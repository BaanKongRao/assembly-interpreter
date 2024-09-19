package Instruction;

import Utils.Word;

/**
 * Interface for all instructions.
 * @see Instruction.AbInstruction
 */
public interface Instruction {
    public void errorCheck();

    public Word toBinary();
    
    public String toBinaryString();

    public void execute();

    @Override
    public String toString();
}
