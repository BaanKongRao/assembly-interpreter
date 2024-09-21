package Instruction;

import java.util.Map;

import Utils.Position;
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
    public void errorCheck(Map<String, Integer> labelsMap) {
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
        Integer offset = (Integer)offsetOrLabel;
        if(inst.equals("lw")) registers[rb] = memory[Word.add(registers[ra], Word.fromInt(offset)).toInt()].clone(); // "lw" function
        else if(inst.equals("sw")) memory[Word.add(registers[ra], Word.fromInt(offset)).toInt()] = registers[rb].clone(); // "sw" function
        else if(inst.equals("beq"))if(registers[rb].equals(registers[ra])) return pc + offset; // "beq" funtions
        return pc + 1;
    }

    @Override
    public String toString() {
        return super.toString(
                String.format("%s, %d, %d, %s", this.inst, this.ra, this.rb, this.offsetOrLabel.toString()));
    }
}
