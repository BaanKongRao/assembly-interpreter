import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
    
    protected static String checkFileNames(String inFilename) throws FileNotFoundException {
        if (!inFilename.endsWith(".fasm")) {
            File file = new File(inFilename + ".fasm");
            if (file.exists()) {
                return inFilename + ".fasm";
            }
            throw new FileNotFoundException("File " + inFilename + ".fasm not found");
        }
        return inFilename;
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
        Map<String, Integer> labelsMap = new HashMap<>();
        for (int i = 0; i < instructions.length; i++) {
            AbInstruction abInstruction = (AbInstruction) instructions[i];
            if (abInstruction.label == null) continue;
            if (labelsPosMap.containsKey(abInstruction.label)) {
                throw new SyntaxError("Label " + abInstruction.label + " is already defined at "
                        + labelsPosMap.get(abInstruction.label), abInstruction.labelStart);
            }
            labelsPosMap.put(abInstruction.label, abInstruction.labelStart);
            labelsMap.put(abInstruction.label, i);
        }
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
