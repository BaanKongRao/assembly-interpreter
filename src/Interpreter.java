import Utils.IntegerOverflowException;
import Utils.SyntaxError;
import Utils.Word;

/**
 * Interpreter class
 * 
 * This class is responsible for interpreting the user input and executing the
 */
public class Interpreter extends AbAssembler {
    private Interpreter() {
    }

    private static final int REGISTER_SIZE = 8;
    private static final int MEMORY_SIZE = 65536;
    private static Word[] registers = new Word[REGISTER_SIZE];
    private static Word[] memory = new Word[MEMORY_SIZE];
    private static int pc = 0;

    public static void interpret(String filename) throws SyntaxError, IntegerOverflowException {
        for (int i = 0; i < REGISTER_SIZE; i++) {
            registers[i] = new Word();
        }
        for (int i = 0; i < MEMORY_SIZE; i++) {
            memory[i] = new Word();
        }

        readFile(filename);

        while (pc < instructions.length) {
            pc = instructions[pc].execute(registers, memory, pc);
        }
    }
}
