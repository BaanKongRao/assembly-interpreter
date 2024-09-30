package Instruction;

import Utils.Bits;
import Utils.IntegerOverflowException;
import Utils.Position;
import Utils.Word;

public class FILL<T> extends AbInstruction {
    public final T numberOrLabel;
    public final Position numberOrLabelStart;

    public FILL(String label, Position labelStart, Position instStart, T numberOrLabel, Position numberOrLabelStart) {
        super(label, labelStart, null, instStart);
        this.numberOrLabel = numberOrLabel;
        this.numberOrLabelStart = numberOrLabelStart;
    }

    @Override
    public void errorCheck() throws IntegerOverflowException {
        Integer number = (Integer) numberOrLabel;
        Bits numberBits = Bits.fromInt(number);
        if (numberBits.length() > 32) {
            throw new IntegerOverflowException("Number is too large to fit in 32 bits", numberOrLabelStart);
        }
    }

    @Override
    public Word toBinary() {
        return Word.fromInt((Integer) numberOrLabel);
    }

    @Override
    public int execute(Word[] registers, Word[] memory, int pc) {
        // TODO Implement this
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }

    @Override
    public String toString() {
        return super.toString(numberOrLabel.toString());
    }
}
