import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

import Token.Token;
import Utils.SyntaxError;

public class Tester {
    public static void main(String[] args) {
        // while (true) {
        //     test();
        // }
        test();
    }

    public static void test() {
        String filePath = "F:\\java\\com-arch\\src\\tester.fasm";
        File file = new File(filePath);
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Token> tokens = null;
        String input = sb.toString();
        try {
            tokens = Lexer.lex(input, filePath);
        } catch (SyntaxError e) {
            System.out.println(e.getMessage());
        }
        for (Token token : tokens) {
            System.out.println(token);
        }
    }
}
