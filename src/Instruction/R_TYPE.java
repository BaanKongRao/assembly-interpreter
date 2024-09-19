package Instruction;

import Utils.Position;
import Utils.Word;

public class R_TYPE extends AbInstruction {
    public final Integer ra;
    public final Position raStart;
    public final Integer rb;
    public final Position rbStart;
    public final Integer rd;
    public final Position rdStart;

    public R_TYPE(String label, Position labelStart, String inst, Position instStart, Integer ra, Position raStart,
            Integer rb, Position rbStart, Integer rd, Position rdStart) {
        super(label, labelStart, inst, instStart);
        this.ra = ra;
        this.raStart = raStart;
        this.rb = rb;
        this.rbStart = rbStart;
        this.rd = rd;
        this.rdStart = rdStart;
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
        return super.toString(this.inst + ", " + this.ra + ", " + this.rb + ", " + this.rd);
    }
}
