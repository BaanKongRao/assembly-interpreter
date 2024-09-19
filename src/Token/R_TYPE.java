package Token;

import Utils.Position;

/**
 * Represents a R_TYPE command
 * 
 * @param value: the instruction of r-type
 * @param start: the start position of the token
 */
public class R_TYPE extends AbToken<String> {
    public R_TYPE(String value, Position start) {
        super(value, start);
    }
}
