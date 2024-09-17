package Token;

import Utils.Position;

/**
 * Represents a NUMBER
 * 
 * @param value: the number value
 * @param start: the start position of the token
 * @param end: the end position of the token
 */
public class NUMBER extends AbToken<Integer> {
    public NUMBER(Integer value, Position start, Position end) {
        super(value, start, end);
    }
}
