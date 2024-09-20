import java.io.IOException;

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
    public static void assemble(String inFilename, String outFilename) throws SyntaxError, IntegerOverflowException {
        try {
            readFile(inFilename);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        try {
            writeFile(outFilename);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }
}
