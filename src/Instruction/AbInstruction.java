package Instruction;

import Utils.Position;

/**
 * Abstract class for all instructions.
 */
public abstract class AbInstruction implements Instruction {
    public final String label;
    public final Position start;

    /**
     * Constructor for AbInstruction
     * @param label the label of the instruction
     * @param start the start position of the instruction
     */
    public AbInstruction(String label, Position start) {
        this.label = label;
        this.start = start;
    }

    public String toBinaryString() {
        return this.toBinary().toString();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + (this.label == null ? "" : " with label:" + this.label) + " at " + start.toString();
    }
}
