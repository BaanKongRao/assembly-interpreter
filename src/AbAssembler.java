import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import Instruction.AbInstruction;
import Instruction.Instruction;
import Instruction.FILL;
import Instruction.I_TYPE;
import Utils.IntegerOverflowException;
import Utils.Position;
import Utils.SyntaxError;

public abstract class AbAssembler {
    protected AbAssembler() {
    }

    protected static Instruction[] instructions;
    protected static final Pattern fileNames = Pattern.compile(".*\\.fasm");
    
    protected static void checkFileNames(String inFilename) {
        if (!fileNames.matcher(inFilename).matches()) {
            throw new Error("Input file must be a .fasm file.");
        }
    }

    protected static String getOutFilename(String inFilename) {
        return inFilename.replace(".fasm", ".fbin");
    }

    protected static void readFile(String filename) throws SyntaxError, IntegerOverflowException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        instructions = Parser.parse(Lexer.lex(sb.toString(), filename)).toArray(new Instruction[0]);

        Map<String, Integer> labelsMap = resolveLabels();
        for (int i = 0; i < instructions.length; i++) {
            switch (instructions[i]) {
                case FILL<?> fill -> {
                    switch (fill.numberOrLabel) {
                        case String s -> {
                            if (labelsMap.containsKey(s)) {
                                instructions[i] = new FILL<Integer>(fill.label, fill.labelStart, fill.instStart, labelsMap.get(s), fill.numberOrLabelStart);
                            } else {
                                throw new SyntaxError("Label " + s + " is not defined", fill.numberOrLabelStart);
                            }
                        }
                        default -> {}
                    }
                }
                case I_TYPE<?> iType -> {
                    switch (iType.offsetOrLabel) {
                        case String s -> {
                            if (labelsMap.containsKey(s)) {
                                if (iType.inst.equals("beq")) {
                                    instructions[i] = new I_TYPE<Integer>(iType.label, iType.labelStart, iType.inst, iType.instStart, iType.ra, iType.raStart, iType.rb, iType.rbStart, labelsMap.get(s) - (i + 1), iType.offsetOrLabelStart);
                                } else {
                                    instructions[i] = new I_TYPE<Integer>(iType.label, iType.labelStart, iType.inst, iType.instStart, iType.ra, iType.raStart, iType.rb, iType.rbStart, labelsMap.get(s), iType.offsetOrLabelStart);
                                }
                            } else {
                                throw new SyntaxError("Label " + s + " is not defined", iType.offsetOrLabelStart);
                            }
                        }
                        default -> {}
                    }
                }
                default -> {}
            }
        }
        for (Instruction instruction : instructions) {
            instruction.errorCheck();
        }
    }

    protected static Map<String, Integer> resolveLabels() throws SyntaxError {
        Map<String, Position> labelsPosMap = new HashMap<>();
        for (Instruction instruction : instructions) {
            AbInstruction abInstruction = (AbInstruction) instruction;
            if (abInstruction.label == null) continue;
            if (labelsPosMap.containsKey(abInstruction.label)) {
                throw new SyntaxError("Label " + abInstruction.label + " is already defined at "
                        + labelsPosMap.get(abInstruction.label), abInstruction.labelStart);
            }
            labelsPosMap.put(abInstruction.label, abInstruction.labelStart);
        }
        Map<String, Integer> labelsMap = new HashMap<>();
        labelsPosMap.forEach((label, pos) -> {
            labelsMap.put(label, pos.line - 1);
        });
        return labelsMap;
    }

    protected static void writeFile(String filename, String output) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            bw.write(output);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }
}
