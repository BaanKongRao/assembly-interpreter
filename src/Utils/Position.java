package Utils;

/**
 * Position class
 * 
 * Represents a position in a file
 * 
 * @param filename the name of the file
 * @param line     the line number
 * @param column   the column number
 */
public class Position {
    public final String filename;
    public final int line;
    public final int column;

    public Position(String filename, int line, int column) {
        if (line < 1) {
            throw new IllegalArgumentException("Line number must be greater than 0");
        }
        if (column < 1) {
            throw new IllegalArgumentException("Column number must be greater than 0");
        }
        this.filename = filename;
        this.line = line;
        this.column = column;
    }

    @Override
    public String toString() {
        return (filename != null ? filename + ":" : "") + line + ":" + column;
    }
}
