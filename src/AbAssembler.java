import java.io.BufferedReader;
import java.io.FileReader;
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
        instructions = Parser.parse(Lexer.lex(sb.toString())).toArray(new Instruction[0]);

        Map<String, Integer> labelsMap = resolveLabels();
        for (Instruction instruction : instructions) {
            instruction.errorCheck(labelsMap);
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
            labelsMap.put(label, pos.line);
        });
        return labelsMap;
    }
}
