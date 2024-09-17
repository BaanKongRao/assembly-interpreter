package Token;

import Utils.Position;

/**
 * Represents a R_TYPE command
 * 
 * @param value: the instruction of r-type
 * @param start: the start position of the token
 * @param end: the end position of the token
 */
public class R_TYPE extends AbToken<String> {
    public R_TYPE(String value, Position start, Position end) {
        super(value, start, end);
    }
}
