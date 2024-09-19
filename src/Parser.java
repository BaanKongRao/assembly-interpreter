import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

import Instruction.Instruction;
import Instruction.*;
import Utils.Position;
import Utils.SyntaxError;

public class Parser {
    /**
     * Parse a list of tokens into a list of instructions.
     * 
     * @param tokens the list of tokens to parse
     * @return the list of instructions
     */
    public static List<Instruction> parse(List<Token.Token> tokens) throws SyntaxError {
        Queue<Token.Token> tokensQueue = new LinkedList<>(tokens);
        List<Instruction> instructions = new LinkedList<>();

        while (tokensQueue.peek() instanceof Token.LABEL
                || tokensQueue.peek() instanceof Token.R_TYPE
                || tokensQueue.peek() instanceof Token.I_TYPE
                || tokensQueue.peek() instanceof Token.J_TYPE
                || tokensQueue.peek() instanceof Token.O_TYPE
                || tokensQueue.peek() instanceof Token.FILL) {
            instructions.add(parseInstruction(tokensQueue));
        }

        Token.Token expectedEOF = tokensQueue.poll();
        if (!(expectedEOF instanceof Token.EOF)) {
            Token.AbToken<?> token = (Token.AbToken<?>) expectedEOF;
            throw new SyntaxError("Expected EOF, got " + token.value, token.start);
        }

        return instructions;
    }

    private static Instruction parseInstruction(Queue<Token.Token> tokensQueue) throws SyntaxError {
        Token.LABEL label = null;
        Token.Token labelOrInst = tokensQueue.poll();
        if (labelOrInst instanceof Token.LABEL) {
            label = (Token.LABEL) labelOrInst;
            labelOrInst = tokensQueue.poll();
        }

        if (labelOrInst instanceof Token.R_TYPE) {
            return parseRType(label, (Token.R_TYPE) labelOrInst, tokensQueue);
        } else if (labelOrInst instanceof Token.I_TYPE) {
            return parseIType(label, (Token.I_TYPE) labelOrInst, tokensQueue);
        } else if (labelOrInst instanceof Token.J_TYPE) {
            return parseJType(label, (Token.J_TYPE) labelOrInst, tokensQueue);
        } else if (labelOrInst instanceof Token.O_TYPE) {
            return parseOType(label, (Token.O_TYPE) labelOrInst, tokensQueue);
        } else if (labelOrInst instanceof Token.FILL) {
            return parseFill(label, (Token.FILL) labelOrInst, tokensQueue);
        } else {
            Token.AbToken<?> token = (Token.AbToken<?>) labelOrInst;
            throw new SyntaxError("Expected instruction, got " + token.value, token.start);
        }
    }

    private static Instruction parseRType(Token.LABEL label, Token.R_TYPE instToken, Queue<Token.Token> tokensQueue)
            throws SyntaxError {
        Token.NUMBER ra = parseNumber(tokensQueue.poll());
        Token.NUMBER rb = parseNumber(tokensQueue.poll());
        Token.NUMBER rd = parseNumber(tokensQueue.poll());

        return new R_TYPE(Optional.ofNullable(label).map(l -> l.value).orElse(null),
                            instToken.value,
                            ra.value,
                            ra.start,
                            rb.value,
                            rb.start,
                            rd.value,
                            rd.start,
                            findStartPos(label, instToken));
    }

    private static Instruction parseIType(Token.LABEL label, Token.I_TYPE instToken, Queue<Token.Token> tokensQueue)
            throws SyntaxError {
        Token.NUMBER ra = parseNumber(tokensQueue.poll());
        Token.NUMBER rb = parseNumber(tokensQueue.poll());
        Token.Token expectedOffsetOrLabel = tokensQueue.poll();
        String labelValue = Optional.ofNullable(label).map(l -> l.value).orElse(null);
        Position startPos = findStartPos(label, instToken);
        try {
            Token.NUMBER offset = parseNumber(expectedOffsetOrLabel);
            return new I_TYPE<Integer>(labelValue,
                                        instToken.value,
                                        ra.value,
                                        ra.start,
                                        rb.value,
                                        rb.start,
                                        offset.value,
                                        offset.start,
                                        startPos);
        } catch (SyntaxError e) {
            try {
                Token.LABEL labelToken = parseLabel(expectedOffsetOrLabel);
                return new I_TYPE<String>(labelValue,
                                            instToken.value,
                                            ra.value,
                                            ra.start,
                                            rb.value,
                                            rb.start,
                                            labelToken.value,
                                            labelToken.start,
                                            startPos);
            } catch (SyntaxError e2) {
                Token.AbToken<?> token = (Token.AbToken<?>) expectedOffsetOrLabel;
                throw new SyntaxError("Expected Offset or Label, got " + token.value, token.start);
            }
        }
    }

    private static Instruction parseJType(Token.LABEL label, Token.J_TYPE instToken, Queue<Token.Token> tokensQueue)
            throws SyntaxError {
        Token.NUMBER ra = parseNumber(tokensQueue.poll());
        Token.NUMBER rb = parseNumber(tokensQueue.poll());

        return new J_TYPE(Optional.ofNullable(label).map(l -> l.value).orElse(null),
                            instToken.value,
                            ra.value,
                            ra.start,
                            rb.value,
                            rb.start,
                            findStartPos(label, instToken));
    }

    private static Instruction parseOType(Token.LABEL label, Token.O_TYPE instToken, Queue<Token.Token> tokensQueue)
            throws SyntaxError {
        return new O_TYPE(Optional.ofNullable(label).map(l -> l.value).orElse(null),
                            instToken.value,
                            findStartPos(label, instToken));
    }

    private static Instruction parseFill(Token.LABEL label, Token.FILL instToken, Queue<Token.Token> tokensQueue)
            throws SyntaxError {
        Token.Token expectedNumberOrLabel = tokensQueue.poll();
        String labelValue = Optional.ofNullable(label).map(l -> l.value).orElse(null);
        Position startPos = findStartPos(label, instToken);
        try {
            Token.NUMBER number = parseNumber(expectedNumberOrLabel);
            return new FILL<Integer>(labelValue, number.value, number.start, startPos);
        } catch (SyntaxError e) {
            try {
                Token.LABEL labelToken = parseLabel(expectedNumberOrLabel);
                return new FILL<String>(labelValue, labelToken.value, labelToken.start, startPos);
            } catch (SyntaxError e2) {
                Token.AbToken<?> token = (Token.AbToken<?>) expectedNumberOrLabel;
                throw new SyntaxError("Expected Number or Label, got " + token.value, token.start);
            }
        }
    }

    private static Token.NUMBER parseNumber(Token.Token token) throws SyntaxError {
        if (token instanceof Token.NUMBER) {
            return (Token.NUMBER) token;
        } else {
            Token.AbToken<?> abToken = (Token.AbToken<?>) token;
            throw new SyntaxError("Expected Number, got " + abToken.value, abToken.start);
        }
    }

    private static Token.LABEL parseLabel(Token.Token token) throws SyntaxError {
        if (token instanceof Token.LABEL) {
            return (Token.LABEL) token;
        } else {
            Token.AbToken<?> abToken = (Token.AbToken<?>) token;
            throw new SyntaxError("Expected Label, got " + abToken.value, abToken.start);
        }
    }

    private static Position findStartPos(Token.LABEL label,Token.AbToken<?> token) {
        return label == null ? token.start : label.start;
    }
}
