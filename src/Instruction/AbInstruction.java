package Instruction;

import java.util.Optional;

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
                + Optional.ofNullable(this.label).map(l -> " with label:" + l).orElse("") + " at "
                + Optional.ofNullable(this.labelStart).map(Position::toString).orElse(this.instStart.toString());
    }
}
