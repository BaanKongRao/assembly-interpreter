package Token;

import Utils.Position;

/**
 * Represents a O_TYPE command
 * 
 * @param value: the instruction of o-type
 * @param start: the start position of the token
 */
public class O_TYPE extends AbToken<String> {
    public O_TYPE(String value, Position start) {
        super(value, start);
    }
}
