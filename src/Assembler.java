import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

import Instruction.Instruction;
import Utils.IntegerOverflowException;
import Utils.SyntaxError;

/**
 * Assembler class is used to assemble the source code into machine code.
 */
public class Assembler extends AbAssembler {
    private Assembler() {
    }

    /**
     * Assemble the source code into machine code.
     * 
     * @param filename: the name of the file to assemble
     */
    public static void assemble(String inFilename) throws SyntaxError, IntegerOverflowException {
        Pattern fileNames = Pattern.compile(".*\\.fasm");
        if (!fileNames.matcher(inFilename).matches()) {
            throw new Error("Input file must be a .fasm file.");
        }
        readFile(inFilename);
        writeFile(inFilename.replace(".fasm", ".fbin"));
    }

    private static void writeFile(String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Instruction instruction : instructions) {
                bw.write(instruction.toBinaryString().replace("0b", ""));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }
}
