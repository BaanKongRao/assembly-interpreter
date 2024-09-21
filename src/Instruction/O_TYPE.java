package Instruction;

import java.util.Map;

import Utils.Position;
import Utils.Word;

public class O_TYPE extends AbInstruction {
    public O_TYPE(String label, Position labelStart, String inst, Position instStart) {
        super(label, labelStart, inst, instStart);
    }

    @Override
    public void errorCheck(Map<String, Integer> labelsMap) {
        // TODO Implement this
        //throw new UnsupportedOperationException("Unimplemented method 'errorCheck'");
    }

    @Override
    public Word toBinary() {
        Word OWord = new Word(); 
        //if inst = halt, use opcode = 110
        OWord.set(23, true);
        OWord.set(24, true);
        //if inst = noop, use opcode = 111
        if(inst.equals("noop")) OWord.set(22, true);
        return OWord;
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
