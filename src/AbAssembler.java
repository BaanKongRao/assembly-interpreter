import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import Instruction.AbInstruction;
import Instruction.Instruction;
import Utils.IntegerOverflowException;
import Utils.Position;
import Utils.SyntaxError;

public abstract class AbAssembler {
    protected AbAssembler() {
    }

    protected static Instruction[] instructions;

    protected static void readFile(String filename) throws IOException, SyntaxError, IntegerOverflowException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }
        br.close();
        instructions = Parser.parse(Lexer.lex(sb.toString())).toArray(new Instruction[0]);

        Map<String, Integer> labelsMap = resolveLabels();
        for (AbInstruction instruction : (AbInstruction[]) instructions) {
            instruction.errorCheck(labelsMap);
        }
    }

    protected static void writeFile(String filename) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
        for (Instruction instruction : instructions) {
            bw.write(instruction.toBinaryString());
            bw.newLine();
        }
        bw.close();
    }

    protected static Map<String, Integer> resolveLabels() throws SyntaxError {
        Map<String, Position> labelsPosMap = new HashMap<>();
        for (AbInstruction instruction : (AbInstruction[]) instructions) {
            if (labelsPosMap.containsKey(instruction.label)) {
                throw new SyntaxError("Label " + instruction.label + " is already defined at "
                        + labelsPosMap.get(instruction.label), instruction.labelStart);
            }
            labelsPosMap.put(instruction.label, instruction.instStart);
        }
        Map<String, Integer> labelsMap = new HashMap<>();
        labelsPosMap.forEach((label, pos) -> {
            labelsMap.put(label, pos.line());
        });
        return labelsMap;
    }
}
