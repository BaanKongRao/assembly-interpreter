package Token;

import Utils.Position;

/**
 * Abstract Token class
 * 
 * @param <T> the type of the value of the token
 * 
 *            Basic implementation of the Token interface
 *            including the value of the token and the start and end positions
 */
public abstract class AbToken<T> implements Token {
    public final T value;
    public final Position start;

    /**
     * Constructor for AbToken
     * 
     * @param value the value of the token
     * @param start the start position of the token
     */
    public AbToken(T value, Position start) {
        this.value = value;
        this.start = start;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(" + this.value + ")" + " at " + start.toString();
    }
}
