package Utils;

/**
 * SyntaxError is an exception that is thrown when a syntax error is encountered
 * 
 * @param message: the error message
 * @param start:   the start position of the error
 */
public class SyntaxError extends Exception {
    public SyntaxError(String message, Position start) {
        super(message + " at " + start.toString());
    }

}
