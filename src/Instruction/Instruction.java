package Instruction;

import java.util.Map;

import Utils.IntegerOverflowException;
import Utils.SyntaxError;
import Utils.Word;

/**
 * Interface for all instructions.
 * 
 * @see Instruction.AbInstruction
 */
public interface Instruction {
    public void errorCheck(Map<String, Integer> labelsMap) throws SyntaxError, IntegerOverflowException;

    public Word toBinary();

    public String toBinaryString();

    public int execute(Word[] registers, Word[] memory, int pc);

    @Override
    public String toString();
}
