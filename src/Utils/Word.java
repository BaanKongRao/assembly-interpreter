package Utils;

public class Word extends Bits {
    /**
     * Creates a new bit set of size 32.
     */
    public Word() {
        super(32);
    }

    @Override
    public String toBinaryString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.length(); i++) {
            sb.insert(0, this.get(i) ? "1" : "0");
        }
        for (int i = this.length(); i < bitsize; i++) {
            sb.insert(0, "0");
        }
        sb.insert(0, "0b");
        return sb.toString();
    }

    @Override
    public String toHexString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.length(); i += 4) {
            int n = 0;
            for (int j = 0; j < 4; j++) {
                n += this.get(i + j) ? (1 << j) : 0;
            }
            sb.insert(0, Integer.toHexString(n));
        }
        for (int i = this.length(); i < bitsize; i += 4) {
            sb.insert(0, "0");
        }
        sb.insert(0, "0x");
        return sb.toString();
    }

    /**
     * Creates a new word from a integer.
     * @param i the integer
     * @return the new word
     */
    public static Word fromInt(int i) {
        // return (Word) Bits.fromInt(i);
        Word word = new Word();
        for (int j = 0; j < word.bitsize; j++) {
            word.set(j, (i & (1 << j)) != 0);
        }
        return word;
    }
    
    /**
     * Creates a new word from a long.
     * @param l the long
     * @return the new word
     */
    public static Word fromLong(long l) {
        // return (Word) Bits.fromLong(l);
        Word word = new Word();
        for (int j = 0; j < word.bitsize; j++) {
            word.set(j, (l & (1L << j)) != 0);
        }
        return word;
    }

    @Override
    public Word clone() {
        return (Word) super.clone();
    }

    /**
     * Performs bitwise AND operation on two words.
     * 
     * @param word1 the first word
     * @param word2 the second word
     * @return the new word that is the result of the operation
     */
    public static Word and(Word word1, Word word2) {
        Word result = word1.clone();
        result.and(word2);
        return result;
    }

    /**
     * Adds two words.
     * 
     * @param word1 the first word
     * @param word2 the second word
     * @return the sum
     */
    public static Word add(Word word1, Word word2) {
        Word result = word1.clone();
        result.add(word2);
        return result;
    }
}
