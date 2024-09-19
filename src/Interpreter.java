import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import Instruction.Instruction;
import Utils.IntegerOverflowException;
import Utils.SyntaxError;
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
        for (int i = 0; i < REGISTER_SIZE; i++) {
            registers[i] = new Word();
        }
        for (int i = 0; i < MEMORY_SIZE; i++) {
            memory[i] = new Word();
        }

        try {
            readfile(filename);
        } catch (SyntaxError | IOException e) {
            e.printStackTrace();
            return;
        }

        for (int i = 0; i < instructions.length; i++) {
            try {
                instructions[i].errorCheck();
            } catch (SyntaxError | IntegerOverflowException e) {
                e.printStackTrace();
                return;
            }
            memory[i] = instructions[i].toBinary();
        }

        while (pc < instructions.length) {
            pc = instructions[pc].execute(registers, memory, pc);
        }
    }

    private static void readfile(String filename) throws SyntaxError, IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }
        br.close();
        instructions = Parser.parse(Lexer.lex(sb.toString())).toArray(new Instruction[0]);
    }
}
