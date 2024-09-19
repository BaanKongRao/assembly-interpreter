package Token;

import Utils.Position;

/**
 * Represents I_TYPE instruction command
 * 
 * @param value: the instruction of i-type
 * @param start: the start position of the token
 */
public class I_TYPE extends AbToken<String> {
    public I_TYPE(String value, Position start) {
        super(value, start);
    }
}
