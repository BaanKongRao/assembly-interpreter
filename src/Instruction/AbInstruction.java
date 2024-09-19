package Instruction;

import Utils.Position;

/**
 * Abstract class for all instructions.
 */
public abstract class AbInstruction implements Instruction {
    public final String label;
    public final String inst;
    public final Position start;

    /**
     * Constructor for AbInstruction
     * @param label the label of the instruction
     * @param start the start position of the instruction
     */
    public AbInstruction(String label, String inst, Position start) {
        this.label = label;
        this.inst = inst;
        this.start = start;
    }

    public String toBinaryString() {
        return this.toBinary().toString();
    }
}
