package Instruction;

import java.util.Map;

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
    public void errorCheck(Map<String, Integer> labelsMap) {
        // TODO Implement this
        //throw new UnsupportedOperationException("Unimplemented method 'errorCheck'");
    }

    @Override
    public Word toBinary() {
        Word JWord = new Word();
        JWord.set(22); //set jalr opcode into instruction with 101
        JWord.set(24);
        JWord.regToWord(ra,21); //put binary bits of regA into instruction
        JWord.regToWord(rb,18); //put binary bits of regB into instruction
        return JWord;
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
