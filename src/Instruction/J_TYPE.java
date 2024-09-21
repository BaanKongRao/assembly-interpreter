package Instruction;

import Utils.Position;
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
        registers[rb] = Word.fromInt(pc + 1);
        return registers[ra].toInt();
    }

    @Override
    public String toString() {
        return super.toString(String.format("%s, %d, %d", this.inst, this.ra, this.rb));
    }

}
