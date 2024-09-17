package Utils;

/**
 * Position class
 * 
 * Represents a position in a file
 * @param filename the name of the file
 * @param line the line number
 * @param column the column number
 */
public record Position(String filename, int line, int column) {
    public Position {
        if (line < 1) {
            throw new IllegalArgumentException("Line number must be greater than 0");
        }
        if (column < 1) {
            throw new IllegalArgumentException("Column number must be greater than 0");
        }
    }

    @Override
    public String toString() {
        return (filename != null ? filename + ":" : "") + line + ":" + column;
    }
}
