package Instruction;

import Utils.Position;
import Utils.Word;

public class I_TYPE<T> extends AbInstruction {
    public final String inst;
    public final Integer ra;
    public final Integer rb;
    public final T imm;

    public I_TYPE(String label, String inst, Integer ra, Integer rb, T imm, Position start) {
        super(label, start);
        this.inst = inst;
        this.ra = ra;
        this.rb = rb;
        this.imm = imm;
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
        return this.getClass().getSimpleName() + "(" + this.inst + ", " + this.ra + ", " + this.rb + ", " + this.imm
                + ")" + (this.label == null ? "" : " with label:" + this.label) + " at " + start.toString();
    }
}
