package Instruction;

import Utils.Bits;
import Utils.IntegerOverflowException;
import Utils.Position;
import Utils.SyntaxError;
import Utils.Word;

public class I_TYPE<T> extends AbInstruction {
    public final Integer ra;
    public final Position raStart;
    public final Integer rb;
    public final Position rbStart;
    public final T offsetOrLabel;
    public final Position offsetOrLabelStart;

    public I_TYPE(String label, Position labelStart, String inst, Position instStart, Integer ra, Position raStart,
            Integer rb, Position rbStart, T offsetOrLabel, Position offsetOrLabelStart) {
        super(label, labelStart, inst, instStart);
        this.ra = ra;
        this.raStart = raStart;
        this.rb = rb;
        this.rbStart = rbStart;
        this.offsetOrLabel = offsetOrLabel;
        this.offsetOrLabelStart = offsetOrLabelStart;
    }

    @Override
    public void errorCheck() throws SyntaxError, IntegerOverflowException {
        if (ra < 0 || ra > 7) throw new SyntaxError("Invalid register. Valid register range is 0 to 7.", raStart);
        if (rb < 0 || rb > 7) throw new SyntaxError("Invalid register. Valid register range is 0 to 7.", rbStart);

        Integer offset = (Integer) offsetOrLabel;
        Bits offsetBits = Bits.fromInt(offset);
        if (offsetBits.length() > 16) throw new IntegerOverflowException("Invalid offset. Valid offset range is -32768 to 32767.", offsetOrLabelStart);
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
        return super.toString(
                String.format("%s, %d, %d, %s", this.inst, this.ra, this.rb, this.offsetOrLabel.toString()));
    }
}
