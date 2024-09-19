package Utils;

/**
 * IntegerOverflowException is an exception that is thrown when an integer
 * overflow is encountered
 * 
 * @param message: the error message
 * @param start:   the start position of the error
 */
public class IntegerOverflowException extends Exception {
    public IntegerOverflowException(String message, Position start) {
        super(message + " at " + start.toString());
    }
}
