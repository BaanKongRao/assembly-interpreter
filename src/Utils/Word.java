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

    @Override
    public Word clone() {
        Word word = new Word();
        for (int i = 0; i < this.length(); i++) {
            word.set(i, this.get(i));
        }
        return word;
    }
    
    /**
     * Performs bitwise AND operation on two words.
     * @param word1 the first word
     * @param word2 the second word
     * @return the new word that is the result of the operation
     */
    public static Word and(Word word1, Word word2) {
        Word result = new Word();
        for (int i = 0; i < word1.length(); i++) {
            result.set(i, word1.get(i) && word2.get(i));
        }
        return result;
    }

    /**
     * Adds two words.
     * @param word1 the first word
     * @param word2 the second word
     * @return the sum
     */
    public static Word add(Word word1, Word word2) {
        Word sum = new Word();
        boolean carry = false;
        for (int i = 0; i < word1.length(); i++) {
            boolean a = word1.get(i);
            boolean b = word2.get(i);
            sum.set(i, a ^ b ^ carry);
            carry = (a && b) || (a && carry) || (b && carry);
        }
        return sum;
    }
}
