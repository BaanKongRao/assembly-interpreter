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
        Bits instBits = null;
        Bits raBits = Bits.fromInt(ra);
        Bits rbBits = Bits.fromInt(rb);
        Bits offsetBits = Bits.fromInt((Integer) offsetOrLabel);
        switch (inst) {
            case "lw" -> instBits = Bits.fromInt(0b010);
            case "sw" -> instBits = Bits.fromInt(0b011);
            case "beq" -> instBits = Bits.fromInt(0b100);
        }
        Word word = new Word();
        for (int i = 0; i < 16; i++) {
            word.set(i, offsetBits.get(i));
        }
        for (int i = 16; i < 19; i++) {
            word.set(i, raBits.get(i - 16));
        }
        for (int i = 19; i < 22; i++) {
            word.set(i, rbBits.get(i - 19));
        }
        for (int i = 22; i < 25; i++) {
            word.set(i, instBits.get(i - 22));
        }
        return word;
    }

    @Override
    public int execute(Word[] registers, Word[] memory, int pc) {
        Integer offset = (Integer)offsetOrLabel;
        if(inst.equals("lw")) registers[rb] = memory[Word.add(registers[ra], Word.fromInt(offset)).toInt()].clone();
        else if(inst.equals("sw")) memory[Word.add(registers[ra], Word.fromInt(offset)).toInt()] = registers[rb].clone();
        else if(inst.equals("beq"))if(registers[rb].equals(registers[ra])) return pc + offset;
        return pc + 1;
    }

    @Override
    public String toString() {
        return super.toString(
                String.format("%s, %d, %d, %s", this.inst, this.ra, this.rb, this.offsetOrLabel.toString()));
    }
}
