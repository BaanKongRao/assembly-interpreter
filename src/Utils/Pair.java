package Utils;

/**
 * Pair class
 * @param <L> left element
 * @param <R> right element
 */
public class Pair<L, R> {
    public final L left;
    public final R right;

    /**
     * Constructor for Pair
     * @param left left element
     * @param right right element
     */
    public Pair(L left, R right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Get left element
     * @return left element
     */
    public L left() {
        return this.left;
    }

    /**
     * Get right element
     * @return right element
     */
    public R right() {
        return this.right;
    }

    /**
     * Create a new Pair
     * @param <L> left element type
     * @param <R> right element type
     * @param left left element
     * @param right right element
     * @return new Pair instance
     */
    public static <L, R> Pair<L, R> of(L left, R right) {
        return new Pair<>(left, right);
    }

    @Override
    public String toString() {
        return "(" + this.left + ", " + this.right + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Pair)) {
            return false;
        }
        Pair<?, ?> pair = (Pair<?, ?>) obj;
        return this.left.equals(pair.left) && this.right.equals(pair.right);
    }

    @Override
    public int hashCode() {
        return this.left.hashCode() ^ this.right.hashCode();
    }
}
