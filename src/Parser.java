import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import Instruction.Instruction;
import Instruction.*;
import Utils.Pair;
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
            return parseRType((Token.R_TYPE) labelOrInst, label, tokensQueue);
        } else if (labelOrInst instanceof Token.I_TYPE) {
            return parseIType((Token.I_TYPE) labelOrInst, label, tokensQueue);
        } else if (labelOrInst instanceof Token.J_TYPE) {
            return parseJType((Token.J_TYPE) labelOrInst, label, tokensQueue);
        } else if (labelOrInst instanceof Token.O_TYPE) {
            return parseOType((Token.O_TYPE) labelOrInst, label, tokensQueue);
        } else if (labelOrInst instanceof Token.FILL) {
            return parseFill((Token.FILL) labelOrInst, label, tokensQueue);
        } else {
            Token.AbToken<?> token = (Token.AbToken<?>) labelOrInst;
            throw new SyntaxError("Expected instruction, got " + token.value, token.start);
        }
    }

    private static Instruction parseRType(Token.R_TYPE instToken, Token.LABEL label, Queue<Token.Token> tokensQueue)
            throws SyntaxError {
        Token.NUMBER reg1 = parseNumber(tokensQueue.poll());
        Token.NUMBER reg2 = parseNumber(tokensQueue.poll());
        Token.NUMBER reg3 = parseNumber(tokensQueue.poll());

        Pair<String, Position> labelValueAndStartPos = findLabelValueAndStartPos(label, instToken.start);
        return new R_TYPE(labelValueAndStartPos.left, instToken.value, reg1.value, reg2.value, reg3.value,
                labelValueAndStartPos.right);
    }

    private static Instruction parseIType(Token.I_TYPE instToken, Token.LABEL label, Queue<Token.Token> tokensQueue)
            throws SyntaxError {
        Token.NUMBER reg1 = parseNumber(tokensQueue.poll());
        Token.NUMBER reg2 = parseNumber(tokensQueue.poll());
        Token.Token expectedOffsetOrLabel = tokensQueue.poll();
        try {
            Token.NUMBER offset = parseNumber(expectedOffsetOrLabel);
            Pair<String, Position> labelValueAndStartPos = findLabelValueAndStartPos(label, instToken.start);
            return new I_TYPE<Integer>(labelValueAndStartPos.left, instToken.value, reg1.value, reg2.value,
                    offset.value, labelValueAndStartPos.right);
        } catch (SyntaxError e) {
            try {
                Token.LABEL labelToken = parseLabel(expectedOffsetOrLabel);
                Pair<String, Position> labelValueAndStartPos = findLabelValueAndStartPos(label, instToken.start);
                return new I_TYPE<String>(labelValueAndStartPos.left, instToken.value, reg1.value, reg2.value,
                        labelToken.value, labelValueAndStartPos.right);
            } catch (SyntaxError e2) {
                Token.AbToken<?> token = (Token.AbToken<?>) expectedOffsetOrLabel;
                throw new SyntaxError("Expected Number or Label, got " + token.value, token.start);
            }
        }
    }

    private static Instruction parseJType(Token.J_TYPE instToken, Token.LABEL label, Queue<Token.Token> tokensQueue)
            throws SyntaxError {
        Token.NUMBER reg1 = parseNumber(tokensQueue.poll());
        Token.NUMBER reg2 = parseNumber(tokensQueue.poll());

        Pair<String, Position> labelValueAndStartPos = findLabelValueAndStartPos(label, instToken.start);
        return new J_TYPE(labelValueAndStartPos.left, instToken.value, reg1.value, reg2.value,
                labelValueAndStartPos.right);
    }

    private static Instruction parseOType(Token.O_TYPE instToken, Token.LABEL label, Queue<Token.Token> tokensQueue)
            throws SyntaxError {
        Pair<String, Position> labelValueAndStartPos = findLabelValueAndStartPos(label, instToken.start);
        return new O_TYPE(labelValueAndStartPos.left, instToken.value, labelValueAndStartPos.right);
    }

    private static Instruction parseFill(Token.FILL instToken, Token.LABEL label, Queue<Token.Token> tokensQueue)
            throws SyntaxError {
        Token.Token expectedNumberOrLabel = tokensQueue.poll();
        Pair<String, Position> labelValueAndStartPos = findLabelValueAndStartPos(label, instToken.start);
        try {
            Token.NUMBER imm = parseNumber(expectedNumberOrLabel);
            return new FILL<Integer>(labelValueAndStartPos.left, imm.value, labelValueAndStartPos.right);
        } catch (SyntaxError e) {
            try {
                Token.LABEL labelToken = parseLabel(expectedNumberOrLabel);
                return new FILL<String>(labelValueAndStartPos.left, labelToken.value, labelValueAndStartPos.right);
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

    private static Pair<String, Position> findLabelValueAndStartPos(Token.LABEL lable, Position defaultPos) {
        return new Pair<>(lable == null ? null : lable.value, lable == null ? defaultPos : lable.start);
    }
}
