package Token;

import Utils.Position;

/**
 * Represents a NUMBER
 * 
 * @param value: the number value
 * @param start: the start position of the token
 */
public class NUMBER extends AbToken<Integer> {
    public NUMBER(Integer value, Position start) {
        super(value, start);
    }
}
