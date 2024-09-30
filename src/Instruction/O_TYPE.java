package Instruction;

import Utils.Bits;
import Utils.Position;
import Utils.Word;

public class O_TYPE extends AbInstruction {
    public O_TYPE(String label, Position labelStart, String inst, Position instStart) {
        super(label, labelStart, inst, instStart);
    }

    @Override
    public void errorCheck() {}

    @Override
    public Word toBinary() {
        Bits instBits = null;
        switch (inst) {
            case "halt" -> instBits = Bits.fromInt(0b110);
            case "noop" -> instBits = Bits.fromInt(0b111);
        }
        Word word = new Word();
        for (int i = 22; i < 25; i++) {
            word.set(i, instBits.get(i - 22));
        }
        return word;
    }

    @Override
    public int execute(Word[] registers, Word[] memory, int pc) {
        // TODO Implement this
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }

    @Override
    public String toString() {
        return super.toString(this.inst);
    }

}
