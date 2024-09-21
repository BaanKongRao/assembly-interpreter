package Instruction;

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
        return super.toString(this.inst);
    }

}
