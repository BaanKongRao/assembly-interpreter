package Token;

import Utils.Position;


/**
 * Represents I_TYPE instruction command
 * 
 * @param value: the instruction of i-type
 * @param start: the start position of the token
 * @param end: the end position of the token
 */
public class I_TYPE extends AbToken<String> {
    public I_TYPE(String value, Position start, Position end) {
        super(value, start, end);
    }
}
