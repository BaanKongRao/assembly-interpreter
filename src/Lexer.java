import java.util.List;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Token.*;
import Utils.Position;
import Utils.SyntaxError;

/**
 * Lexer
 * class to tokenize the input string into tokens list
 * 
 * @param input the input string to be tokenized
 */
public class Lexer {
    /**
     * This enum represents token types and their corresponding regex patterns.
     */
    private enum TokenMatcher {
        Line_terminator("\r?\n"),
        Whitespace("[\s\t]+"),
        Comment(";.*"),
        Label("[a-zA-Z][a-zA-Z0-9]{0,6}"),
        R_type("(add|nand)"),
        I_type("(lw|sw|beq)"),
        J_type("(jalr)"),
        O_type("(halt|noop)"),
        Fill("\\.fill"),
        Number("-?\\d+");

        private final Pattern pattern;

        /**
         * The constructor for the enum takes a regex string and compiles it into a
         * Pattern object.
         * 
         * @param regex The regex string to be compiled into a Pattern object.
         */
        private TokenMatcher(String regex) {
            this.pattern = Pattern.compile("^" + regex);
        }

        /**
         * This method takes a string and returns the index of the first substring that
         * matches the regex pattern.
         * 
         * @param input The string to be matched.
         * @return The index of the first substring that matches the regex pattern.
         */
        public int endOfMatch(String input) {
            Matcher matcher = pattern.matcher(input);
            return matcher.find() ? matcher.end() : -1;
        }

        /**
         * This method takes a string and returns a Token object based on the type of
         * the enum.
         * 
         * @param s      The string to be converted into a Token object.
         * @param start  The start position of the token.
         * @param end    The end position of the token.
         * @return A Token object based on the type of the enum.
         */
        public Token createToken(String s, Position start, Position end) {
            switch (this) {
                case R_type:
                    return new R_TYPE(s, start, end);
                case I_type:
                    return new I_TYPE(s, start, end);
                case J_type:
                    return new J_TYPE(s, start, end);
                case O_type:
                    return new O_TYPE(s, start, end);
                case Fill:
                    return new FILL(start, end);
                case Label:
                    return new LABEL(s, start, end);
                case Number:
                    return new NUMBER(Integer.parseInt(s), start, end);
                default:
                    return null;
            }
        }
    }

    /**
     * This method takes a string and returns a list of Token objects.
     * 
     * @param string The string to be tokenized.
     * @return A list of Token objects.
     * @throws SyntaxError
     */
    public static List<Token> lex(String string) throws SyntaxError {
        return lex(string, null);
    }

    /**
     * This method takes a string and a filename and returns a list of Token objects.
     * 
     * @param string The string to be tokenized.
     * @param filename The name of the file.
     * @return A list of Token objects.
     * @throws SyntaxError
     */
    public static List<Token> lex(String string, String filename) throws SyntaxError {
        List<Token> tokens = new LinkedList<Token>();
        int index = 0;
        int line = 1;
        int column = 1;
        while (index < string.length()) {
            int endOfMatch = -1;
            int longestMatch = -1;
            TokenMatcher tokenMatcher = null;
            for (TokenMatcher matcher : TokenMatcher.values()) {
                endOfMatch = matcher.endOfMatch(string.substring(index));
                if (endOfMatch >= longestMatch) {
                    longestMatch = endOfMatch;
                    tokenMatcher = matcher;
                }
            }
            if (longestMatch == -1) {
                throw new SyntaxError("Unexpected character", new Position(filename, line, column));
            }
            if (tokenMatcher == TokenMatcher.Line_terminator) {
                line++;
                column = 1;
            } else if (tokenMatcher == TokenMatcher.Whitespace || tokenMatcher == TokenMatcher.Comment) {
                column += longestMatch;
            } else {
                tokens.add(tokenMatcher.createToken(string.substring(index, index + longestMatch),
                        new Position(filename, line, column), new Position(filename, line, column + longestMatch)));
                column += longestMatch;
            }
            index += longestMatch;
        }
        tokens.add(new EOF(new Position(filename, line, column), new Position(filename, line, column)));
        return tokens;
    }
}
