package Token;

import Utils.Position;

/**
 * Represents the end of file
 * 
 * @param start: the start position of the token
 */
public class EOF extends AbToken<Void> {
    public EOF(Position start) {
        super(null, start);
    }

    @Override
    public String toString() {
        return "EOF" + " at " + start.toString();
    }
}
