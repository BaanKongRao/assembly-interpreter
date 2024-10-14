import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.List;

import Instruction.Instruction;
import Token.Token;
import Utils.SyntaxError;
import Utils.Word;

public class Tester {
    public static void main(String[] args) {
        // testBits();
        // testLexer();
        // testParser();
        // testAssembler();
        // testInterpreter();
        // testAssemblerTermInputFileName();
        // testInterpreterTermInputFileName();
        testByTermInputFileName();
        // testInterpreterByFile("src/multiplication.fasm");
        // testInterpreterByFile("src/combination.fasm");
    }

    public static void testBits() {
        Word word1 = new Word();
        Word word2 = new Word();
        word1.set(1);
        word1.set(3);
        word2.set(0);
        word2.set(2);
        System.out.println("--------------Word1--------------");
        System.out.println(word1);
        System.out.println(word1.toHexString());
        System.out.println(word1.toLong());
        System.out.println("--------------Word2--------------");
        System.out.println(word2);
        System.out.println(word2.toHexString());
        System.out.println(word2.toLong());
        System.out.println("--------------Word1 & Word2--------------");
        Word and = word1.clone();
        and.and(word2);
        System.out.println(and);
        System.out.println("--------------Word1 | Word2--------------");
        Word or = word1.clone();
        or.or(word2);
        System.out.println(or);
        System.out.println("--------------Word1 ^ Word2--------------");
        Word xor = word1.clone();
        xor.xor(word2);
        System.out.println(xor);
        System.out.println("--------------~Word1--------------");
        Word not1 = word1.clone();
        not1.flip(0, 32);
        System.out.println(not1);
        System.out.println("--------------Word1 + Word2--------------");
        Word add = Word.add(word1, word2);
        System.out.println(add);
        System.out.println(add.toHexString());
        System.out.println(add.toLong());
        System.out.println("-------------------------------------------");
    }

    public static void testLexer() {
        String filePath = "src/fiveToZero.fasm";
        File file = new File(filePath);
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        List<Token> tokens = null;
        String input = sb.toString();
        try {
            tokens = Lexer.lex(input, filePath);
        } catch (SyntaxError e) {
            e.printStackTrace();
            return;
        }
        for (Token token : tokens) {
            System.out.println(token);
        }
    }

    public static void testParser() {
        String filePath = "src/4timeInput.fasm";
        File file = new File(filePath);
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        List<Instruction> instructions = null;
        String input = sb.toString();
        try {
            instructions = Parser.parse(Lexer.lex(input, filePath));
        } catch (SyntaxError e) {
            e.printStackTrace();
            return;
        }
        for (Instruction instruction : instructions) {
            System.out.println(instruction);
        }
    }

    public static void testAssembler() {
        testAssemblerByFile("src/fiveToZero.fasm");
        testAssemblerByFile("src/4timeInput.fasm");
    }

    public static void testInterpreter() {
        testInterpreterByFile("src/fiveToZero.fasm");
        testInterpreterByFile("src/4timeInput.fasm");
    }

    public static void testAssemblerByFile(String inFilePath) {
        try {
            Assembler.assemble(inFilePath);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    public static void testInterpreterByFile(String inFilePath) {
        try {
            Interpreter.interpret(inFilePath);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    public static void testAssemblerTermInputFileName() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String input;
            while (true) {
                System.out.print("Enter the file name: ");
                input = br.readLine();
                if (input.equals("exit")) {
                    break;
                }
                testAssemblerByFile(input);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    public static void testInterpreterTermInputFileName() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String input;
            while (true) {
                System.out.print("Enter the file name: ");
                input = br.readLine();
                if (input.equals("exit")) {
                    break;
                }
                testInterpreterByFile(input);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    public static void testByTermInputFileName() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Type 'exit' to exit the program.");
            while (true) {
                String mode;
                String input;
                System.out.print("Enter the file name: ");
                input = br.readLine();
                if (input.equals("exit")) {
                    break;
                }
                System.out.println("Mode");
                System.out.println("1: Assembler");
                System.out.println("2: Interpreter");
                System.out.print("Select the mode: ");
                mode = br.readLine();
                if (input.equals("exit")) {
                    break;
                }
                switch (Integer.parseInt(mode)) {
                    case 1:
                        testAssemblerByFile(input);
                        break;
                    case 2:
                        testInterpreterByFile(input);
                        break;
                    default:
                        System.out.println("Invalid mode.");
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
}
