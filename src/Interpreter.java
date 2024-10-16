import java.io.FileNotFoundException;

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
    private static int pcOld = 0;
    private static int pc = 0;
    private static int instCount = 0;

    private static StringBuilder sb = new StringBuilder();

    public static void interpret(String filename) throws SyntaxError, IntegerOverflowException, FileNotFoundException {
        filename = checkFileNames(filename);
        for (int i = 0; i < REGISTER_SIZE; i++) {
            registers[i] = new Word();
        }
        for (int i = 0; i < MEMORY_SIZE; i++) {
            memory[i] = new Word();
        }
        pcOld = 0;
        pc = 0;
        instCount = 0;
        
        sb.setLength(0);

        readFile(filename);
        initRegisters();
        initMemory();

        printMemory();
        while (pc < instructions.length) {
            printState();
            pcOld = pc;
            pc = instructions[pc].execute(registers, memory, pc);
            registers[0] = new Word();
            instCount++;
        }
        if (pc == Integer.MAX_VALUE) {
            pc = pcOld + 1;
            System.out.println("Machine halted");
            sb.append("Machine halted\n");
        }
        System.out.println("Instructions executed: " + instCount);
        sb.append("Instructions executed: ").append(instCount).append("\n");

        System.out.println("Final memory state:");
        sb.append("Final machine state:\n");
        printState();

        writeFile(filename.replace(".fasm", ".out"), sb.toString());
    }

    private static void initRegisters() {
        registers[5] = Word.fromInt(instructions.length - 1);
    }

    private static void initMemory() {
        for (int i = 0; i < instructions.length; i++) {
            memory[i] = instructions[i].toBinary();
        }
    }

    private static void printMemory() {
        printMemory(0);
    }

    private static void printMemory(int indent) {
        for (int i = 0; i < MEMORY_SIZE; i++) {
            if (memory[i].toInt() != 0 || i < instructions.length) {
                System.out.println("    ".repeat(indent) + "memory[" + i + "] = " + memory[i].toInt());
                sb.append("    ".repeat(indent)).append("memory[").append(i).append("] = ").append(memory[i].toInt()).append("\n");
            }
        }
    }

    private static void printRegisters(int indent) {
        for (int i = 0; i < REGISTER_SIZE; i++) {
            System.out.println("    ".repeat(indent) + "registers[" + i + "] = " + registers[i].toInt());
            sb.append("    ".repeat(indent)).append("registers[").append(i).append("] = ").append(registers[i].toInt()).append("\n");
        }
    }

    private static void printState() {
        System.out.println("@@@");
        sb.append("@@@\n");
        System.out.println("state:");
        sb.append("state:\n");
        System.out.println("    pc " + pc);
        sb.append("    pc ").append(pc).append("\n");
        System.out.println("    memory:");
        sb.append("    memory:\n");
        printMemory(2);
        System.out.println("    registers:");
        sb.append("    registers:\n");
        printRegisters(2);
        System.out.println("end state");
        sb.append("end state\n");
    }
}
