package Instruction;

import Utils.Position;
import Utils.Word;

public class R_TYPE extends AbInstruction {
    public final String inst;
    public final Integer ra;
    public final Integer rb;
    public final Integer rd;

    public R_TYPE(String label, String inst, Integer ra, Integer rb, Integer rd, Position start, Position end) {
        super(label, start, end);
        this.inst = inst;
        this.ra = ra;
        this.rb = rb;
        this.rd = rd;
    }

    @Override
    public Word toBinary() {
        // TODO Implement this
        throw new UnsupportedOperationException("Unimplemented method 'toBinary'");
    }

    @Override
    public void execute() {
        // TODO Implement this
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(" + this.inst + ", " + this.ra + ", " + this.rb + ", " + this.rd
                + ")" + (this.label == null ? "" : " with label:" + this.label) + " at " + start.toString();
    }
}
