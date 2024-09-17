package Token;

import Utils.Position;

/**
 * Represents J_TYPE instruction command
 * 
 * @param value: the instruction of j-type
 * @param start: the start position of the token
 * @param end: the end position of the token
 */
public class J_TYPE extends AbToken<String> {
    public J_TYPE(String value, Position start, Position end) {
        super(value, start, end);
    }
}
