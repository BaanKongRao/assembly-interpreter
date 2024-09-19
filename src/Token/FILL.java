package Token;

import Utils.Position;

/**
 * Represents a FILL command
 * 
 * @param start: the start position of the token
 */
public class FILL extends AbToken<Void> {
    public FILL(Position start) {
        super(null, start);
    }

    @Override
    public String toString() {
        return "FILL" + " at " + start.toString();
    }
}
