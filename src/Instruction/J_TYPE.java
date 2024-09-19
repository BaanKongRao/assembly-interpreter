package Instruction;

import Utils.Position;
import Utils.Word;

public class J_TYPE extends AbInstruction {
    public final Integer ra;
    public final Position raStart;
    public final Integer rb;
    public final Position rbStart;

    public J_TYPE(String label, String inst, Integer ra, Position raStart, Integer rb, Position rbStart, Position start) {
        super(label, inst, start);
        this.ra = ra;
        this.raStart = raStart;
        this.rb = rb;
        this.rbStart = rbStart;
    }

    @Override
    public void errorCheck() {
        // TODO Implement this
        throw new UnsupportedOperationException("Unimplemented method 'errorCheck'");
    }

    @Override
    public Word toBinary() {
        // TODO Implement this
        throw new UnsupportedOperationException("Unimplemented method 'toBinary'");
    }

    @Override
    public int execute(Word[] registers, Word[] memory, int pc) {
        // TODO Implement this
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(" + this.inst + ", " + this.ra + ", " + this.rb + ")"
                + (this.label == null ? "" : " with label:" + this.label) + " at " + start.toString();
    }

}
