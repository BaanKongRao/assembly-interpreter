package Instruction;

import Utils.Position;
import Utils.Word;

public class FILL<T> extends AbInstruction {
    public final T numberOrLabel;
    public final Position numberOrLabelStart;

    public FILL(String label, T numberOrLabel, Position numberOrLabelStart, Position start) {
        super(label, null, start);
        this.numberOrLabel = numberOrLabel;
        this.numberOrLabelStart = numberOrLabelStart;
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
        return this.getClass().getSimpleName() + "(" + this.numberOrLabel + ")" + (this.label == null ? "" : " with label:" + this.label) + " at " + start.toString();
    }
}
