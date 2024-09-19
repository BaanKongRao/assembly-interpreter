import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

import Instruction.Instruction;
import Instruction.*;
import Utils.Position;
import Utils.SyntaxError;

/**
 * Parse a list of tokens into a list of instructions.
 */
public class Parser {
    private Parser() {
    }

    private static Queue<Token.Token> tokensQueue;

    /**
     * Parse a list of tokens into a list of instructions.
     * 
     * @param tokens the list of tokens to parse
     * @return the list of instructions
     */
    public static List<Instruction> parse(List<Token.Token> tokens) throws SyntaxError {
        tokensQueue = new LinkedList<>(tokens);
        List<Instruction> instructions = new LinkedList<>();

        while (switch (tokensQueue.peek()) {
            case Token.LABEL l -> true;
            case Token.R_TYPE r -> true;
            case Token.I_TYPE i -> true;
            case Token.J_TYPE j -> true;
            case Token.O_TYPE o -> true;
            case Token.FILL f -> true;
            default -> false;
        }) {
            instructions.add(parseInstruction());
        }

        return switch (tokensQueue.peek()) {
            case Token.EOF e -> instructions;
            default -> {
                Token.AbToken<?> token = (Token.AbToken<?>) tokensQueue.peek();
                throw new SyntaxError("Expected EOF, got " + token.value, token.start);
            }
        };
    }

    private static Instruction parseInstruction() throws SyntaxError {
        Token.LABEL label = null;
        if (tokensQueue.peek() instanceof Token.LABEL l) {
            label = (Token.LABEL) l;
            tokensQueue.poll();
        }

        return switch (tokensQueue.peek()) {
            case Token.R_TYPE r -> {
                tokensQueue.poll();
                yield parseRType(label, r);
            }
            case Token.I_TYPE i -> {
                tokensQueue.poll();
                yield parseIType(label, i);
            }
            case Token.J_TYPE j -> {
                tokensQueue.poll();
                yield parseJType(label, j);
            }
            case Token.O_TYPE o -> {
                tokensQueue.poll();
                yield parseOType(label, o);
            }
            case Token.FILL f -> {
                tokensQueue.poll();
                yield parseFill(label, f);
            }
            default -> {
                Token.AbToken<?> token = (Token.AbToken<?>) tokensQueue.peek();
                throw new SyntaxError("Expected instruction, got " + token.value, token.start);
            }
        };
    }

    private static Instruction parseRType(Token.LABEL label, Token.R_TYPE instToken)
            throws SyntaxError {
        Token.NUMBER ra = parseNumber();
        Token.NUMBER rb = parseNumber();
        Token.NUMBER rd = parseNumber();

        return new R_TYPE(Optional.ofNullable(label).map(l -> l.value).orElse(null),
                Optional.ofNullable(label).map(l -> l.start).orElse(null),
                instToken.value,
                instToken.start,
                ra.value,
                ra.start,
                rb.value,
                rb.start,
                rd.value,
                rd.start);
    }

    private static Instruction parseIType(Token.LABEL label, Token.I_TYPE instToken)
            throws SyntaxError {
        Token.NUMBER ra = parseNumber();
        Token.NUMBER rb = parseNumber();
        String labelValue = Optional.ofNullable(label).map(l -> l.value).orElse(null);
        Position labelStart = Optional.ofNullable(label).map(l -> l.start).orElse(null);

        return switch (tokensQueue.peek()) {
            case Token.NUMBER offset -> {
                tokensQueue.poll();
                yield new I_TYPE<Integer>(labelValue,
                        labelStart,
                        instToken.value,
                        instToken.start,
                        ra.value,
                        ra.start,
                        rb.value,
                        rb.start,
                        offset.value,
                        offset.start);
            }
            case Token.LABEL label2 -> {
                tokensQueue.poll();
                yield new I_TYPE<String>(labelValue,
                        labelStart,
                        instToken.value,
                        instToken.start,
                        ra.value,
                        ra.start,
                        rb.value,
                        rb.start,
                        label2.value,
                        label2.start);
            }
            default -> {
                Token.AbToken<?> token = (Token.AbToken<?>) tokensQueue.peek();
                throw new SyntaxError("Expected Offset or Label, got " + token.value, token.start);
            }
        };
    }

    private static Instruction parseJType(Token.LABEL label, Token.J_TYPE instToken)
            throws SyntaxError {
        Token.NUMBER ra = parseNumber();
        Token.NUMBER rb = parseNumber();

        return new J_TYPE(Optional.ofNullable(label).map(l -> l.value).orElse(null),
                Optional.ofNullable(label).map(l -> l.start).orElse(null),
                instToken.value,
                instToken.start,
                ra.value,
                ra.start,
                rb.value,
                rb.start);
    }

    private static Instruction parseOType(Token.LABEL label, Token.O_TYPE instToken)
            throws SyntaxError {
        return new O_TYPE(Optional.ofNullable(label).map(l -> l.value).orElse(null),
                Optional.ofNullable(label).map(l -> l.start).orElse(null),
                instToken.value,
                instToken.start);
    }

    private static Instruction parseFill(Token.LABEL label, Token.FILL instToken)
            throws SyntaxError {
        String labelValue = Optional.ofNullable(label).map(l -> l.value).orElse(null);
        Position labelStart = Optional.ofNullable(label).map(l -> l.start).orElse(null);

        return switch (tokensQueue.peek()) {
            case Token.NUMBER number -> {
                tokensQueue.poll();
                yield new FILL<Integer>(labelValue, labelStart, instToken.start, number.value, number.start);
            }
            case Token.LABEL label2 -> {
                tokensQueue.poll();
                yield new FILL<String>(labelValue, labelStart, instToken.start, label2.value, label2.start);
            }
            default -> {
                Token.AbToken<?> token = (Token.AbToken<?>) tokensQueue.peek();
                throw new SyntaxError("Expected Number or Label, got " + token.value, token.start);
            }
        };
    }

    private static Token.NUMBER parseNumber() throws SyntaxError {
        if (tokensQueue.peek() instanceof Token.NUMBER number) {
            tokensQueue.poll();
            return number;
        } else {
            Token.AbToken<?> token = (Token.AbToken<?>) tokensQueue.peek();
            throw new SyntaxError("Expected Number, got " + token.value, token.start);
        }
    }
}
