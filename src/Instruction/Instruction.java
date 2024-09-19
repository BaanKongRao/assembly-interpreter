package Instruction;

import Utils.IntegerOverflowException;
import Utils.SyntaxError;
import Utils.Word;

/**
 * Interface for all instructions.
 * 
 * @see Instruction.AbInstruction
 */
public interface Instruction {
    public void errorCheck() throws SyntaxError, IntegerOverflowException;

    public Word toBinary();

    public String toBinaryString();

    public int execute(Word[] registers, Word[] memory, int pc);

    @Override
    public String toString();
}
