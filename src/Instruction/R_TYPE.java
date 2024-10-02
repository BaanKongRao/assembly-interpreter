package Instruction;

import Utils.Bits;
import Utils.Position;
import Utils.SyntaxError;
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
    public void errorCheck() throws SyntaxError {
        if (ra < 0 || ra > 7) throw new SyntaxError("Invalid register. Valid register range is 0 to 7.", raStart);
        if (rb < 0 || rb > 7) throw new SyntaxError("Invalid register. Valid register range is 0 to 7.", rbStart);
        if (rd < 0 || rd > 7) throw new SyntaxError("Invalid register. Valid register range is 0 to 7.", rdStart);
    }

    @Override
    public Word toBinary() {
        Bits instBits = null;
        Bits raBits = Bits.fromInt(ra);
        Bits rbBits = Bits.fromInt(rb);
        Bits rdBits = Bits.fromInt(rd);
        switch (inst) {
            case "add" -> instBits = Bits.fromInt(0b000);
            case "nand" -> instBits = Bits.fromInt(0b001);
        }
        Word word = new Word();
        for (int i = 0; i < 3; i++) {
            word.set(i, rdBits.get(i));
        }
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
        if(inst.equals("add")) {
            registers[rd] = Word.add(registers[ra], registers[rb]);
        } else if(inst.equals("nand")) {
            Word result = Word.and(registers[ra],registers[rb]);
            result.flip(0,32);
            registers[rd] = result;
        }
        return pc + 1;
    }

    @Override
    public String toString() {
        return super.toString(String.format("%s, %d, %d, %d", this.inst, this.ra, this.rb, this.rd));
    }
}
