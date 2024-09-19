package Token;

import Utils.Position;

/**
 * Represents LABEL
 * 
 * @param value: the label name
 * @param start: the start position of the token
 */
public class LABEL extends AbToken<String> {
    public LABEL(String value, Position start) {
        super(value, start);
    }
}
