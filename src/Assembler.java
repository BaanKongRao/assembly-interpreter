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
        inFilename = checkFileNames(inFilename);
        readFile(inFilename);
        writeFile(getOutFilename(inFilename), getAssemblerOutput());
    }

    private static String getAssemblerOutput() {
        StringBuilder sb = new StringBuilder();
        for (Instruction instruction : instructions) {
            sb.append(instruction.toBinary().toInt()).append("\n");
        }
        return sb.toString();
    }
}
