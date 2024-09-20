package Instruction;

import java.util.Map;

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
        // TODO Implement this
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }

    @Override
    public String toString() {
        return super.toString(numberOrLabel.toString());
    }
}
