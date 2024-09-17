package Token;

import Utils.Position;

/**
 * Represents a FILL command
 * 
 * @param start: the start position of the token
 * @param end: the end position of the token
 */
public class FILL extends AbToken<Void> {
    public FILL(Position start, Position end) {
        super(null, start, end);
    }

    @Override
    public String toString() {
        return "FILL" + " at " + start.toString();
    }
}
