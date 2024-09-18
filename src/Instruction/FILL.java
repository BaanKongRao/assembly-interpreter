package Instruction;

import Utils.Position;
import Utils.Word;

public class FILL<T> extends AbInstruction {
    public final T imm;

    public FILL(String label, T imm, Position start, Position end) {
        super(label, start, end);
        this.imm = imm;
    }

    @Override
    public Word toBinary() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toBinary'");
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(" + this.imm + ")" + (this.label == null ? "" : " with label:" + this.label) + " at " + start.toString();
    }
}
