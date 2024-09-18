package Instruction;

import Utils.Position;

public abstract class AbInstruction implements Instruction {
    public final String label;
    public final Position start;
    public final Position end;

    public AbInstruction(String label, Position start, Position end) {
        this.label = label;
        this.start = start;
        this.end = end;
    }

    public String toBinaryString() {
        return this.toBinary().toString();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + (this.label == null ? "" : " with label:" + this.label) + " at " + start.toString();
    }
}
