package Token;

import Utils.Position;

/**
 * Represents LABEL
 * 
 * @param value: the label name
 * @param start: the start position of the token
 * @param end: the end position of the token
 */
public class LABEL extends AbToken<String> {
    public LABEL(String value, Position start, Position end) {
        super(value, start, end);
    }
}
