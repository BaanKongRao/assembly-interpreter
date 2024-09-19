package Instruction;

import Utils.Position;
import Utils.Word;

public class O_TYPE extends AbInstruction {
    public O_TYPE(String label, String inst, Position start) {
        super(label, inst, start);
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
        return this.getClass().getSimpleName() + "(" + this.inst + ")"
                + (this.label == null ? "" : " with label:" + this.label) + " at " + start.toString();
    }

}
