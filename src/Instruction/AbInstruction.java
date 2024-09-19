package Instruction;

import Utils.Position;

/**
 * Abstract class for all instructions.
 */
public abstract class AbInstruction implements Instruction {
    public final String label;
    public final Position labelStart;
    public final String inst;
    public final Position instStart;

    /**
     * Constructor for AbInstruction
     * 
     * @param label     the label of the instruction
     * @param instStart the start position of the instruction
     */
    public AbInstruction(String label, Position labelStart, String inst, Position instStart) {
        this.label = label;
        this.labelStart = labelStart;
        this.inst = inst;
        this.instStart = instStart;
    }

    public String toBinaryString() {
        return this.toBinary().toString();
    }

    public String toString(String args) {
        return this.getClass().getSimpleName() + "(" + args + ")"
                + (this.label == null ? "" : " with label:" + this.label) + " at "
                + (this.labelStart == null ? this.instStart.toString() : this.labelStart.toString());
    }
}
