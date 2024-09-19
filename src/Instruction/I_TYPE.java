package Instruction;

import Utils.Position;
import Utils.Word;

public class I_TYPE<T> extends AbInstruction {
    public final Integer ra;
    public final Position raStart;
    public final Integer rb;
    public final Position rbStart;
    public final T offsetOrLabel;
    public final Position offsetOrLabelStart;

    public I_TYPE(String label, String inst, Integer ra, Position raStart, Integer rb, Position rbStart, T offsetOrLabel, Position offsetOrLabelStart, Position start) {
        super(label, inst, start);
        this.ra = ra;
        this.raStart = raStart;
        this.rb = rb;
        this.rbStart = rbStart;
        this.offsetOrLabel = offsetOrLabel;
        this.offsetOrLabelStart = offsetOrLabelStart;
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
    public void execute() {
        // TODO Implement this
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(" + this.inst + ", " + this.ra + ", " + this.rb + ", " + this.offsetOrLabel
                + ")" + (this.label == null ? "" : " with label:" + this.label) + " at " + start.toString();
    }
}
