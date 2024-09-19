import Instruction.Instruction;
import Utils.Word;

/**
 * Interpreter class
 * 
 * This class is responsible for interpreting the user input and executing the
 */
public class Interpreter {
    private Interpreter() {}

    private static final int REGISTER_SIZE = 8;
    private static final int MEMORY_SIZE = 65536;
    private static Word[] registers = new Word[REGISTER_SIZE];
    private static Word[] memory = new Word[MEMORY_SIZE];
    private static Instruction[] instructions;
    private static int pc = 0;

    public static void interpret(String filename) {
        // TODO Implement this
        throw new UnsupportedOperationException("Unimplemented method 'interpret'");
    }
}
