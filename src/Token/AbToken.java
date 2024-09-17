package Token;

import Utils.Position;

/**
 * Abstract Token class
 * @param <T> the type of the value of the token
 * 
 * Basic implementation of the Token interface
 * including the value of the token and the start and end positions
 */
public abstract class AbToken<T> implements Token {
    public final T value;
    public final Position start;
    public final Position end;

    public AbToken(T value, Position start, Position end) {
        this.value = value;
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(" + this.value + ")" + " at " + start.toString();
    }
}
