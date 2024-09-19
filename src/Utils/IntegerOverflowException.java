package Utils;

public class IntegerOverflowException extends Exception {
    public IntegerOverflowException(String message, Position start) {
        super(message + " at " + start.toString());
    }

}
