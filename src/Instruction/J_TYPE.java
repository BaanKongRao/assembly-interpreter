package Instruction;

import Utils.Bits;
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
        Bits instBits = Bits.fromInt(0b101);
        Bits raBits = Bits.fromInt(ra);
        Bits rbBits = Bits.fromInt(rb);
        Word word = new Word();
        for (int i = 16; i < 19; i++) {
            word.set(i, rbBits.get(i - 16));
        }
        for (int i = 19; i < 22; i++) {
            word.set(i, raBits.get(i - 19));
        }
        for (int i = 22; i < 25; i++) {
            word.set(i, instBits.get(i - 22));
        }
        return word;
    }

    @Override
    public int execute(Word[] registers, Word[] memory, int pc) {
        registers[rb] = Word.fromInt(pc + 1);
        return registers[ra].toInt();
    }

    @Override
    public String toString() {
        return super.toString(String.format("%s, %d, %d", this.inst, this.ra, this.rb));
    }

}
