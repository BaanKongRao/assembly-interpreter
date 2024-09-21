package Instruction;

import Utils.Position;
import Utils.SyntaxError;
import Utils.Word;

public class J_TYPE extends AbInstruction {
    public final Integer ra;
    public final Position raStart;
    public final Integer rb;
    public final Position rbStart;

    public J_TYPE(String label, Position labelStart, String inst, Position instStart, Integer ra, Position raStart,
            Integer rb, Position rbStart) {
        super(label, labelStart, inst, instStart);
        this.ra = ra;
        this.raStart = raStart;
        this.rb = rb;
        this.rbStart = rbStart;
    }

    @Override
    public void errorCheck() throws SyntaxError {
        if (ra < 0 || ra > 7) throw new SyntaxError("Invalid register. Valid register range is 0 to 7.", raStart);
        if (rb < 0 || rb > 7) throw new SyntaxError("Invalid register. Valid register range is 0 to 7.", rbStart);
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
        return super.toString(String.format("%s, %d, %d", this.inst, this.ra, this.rb));
    }

}
