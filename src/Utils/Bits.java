package Utils;

import java.util.BitSet;

public class Bits extends BitSet {
    protected int bitsize = 1 << 6;

    /**
     * Creates a new bit set of size 64.
     */
    public Bits() {
        super();
    }

    /**
     * Creates a new bit set of size nbits.
     * 
     * @param nbits the size of the bit set
     */
    public Bits(int nbits) {
        super(nbits);
        bitsize = nbits;
    }

    @Override
    public int size() {
        return bitsize;
    }

    @Override
    public boolean get(int bitIndex) {
        if (bitIndex >= bitsize) {
            throw new IndexOutOfBoundsException("bitIndex: " + bitIndex + " >= bitsize: " + bitsize);
        }
        return super.get(bitIndex);
    }

    @Override
    public Bits get(int fromIndex, int toIndex) {
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException("fromIndex: " + fromIndex + " > toIndex: " + toIndex);
        }
        if (toIndex >= bitsize) {
            throw new IndexOutOfBoundsException("toIndex: " + toIndex + " > bitsize: " + bitsize);
        }
        return (Bits) super.get(fromIndex, toIndex);
    }

    @Override
    public void set(int bitIndex) {
        if (bitIndex >= bitsize) {
            throw new IndexOutOfBoundsException("bitIndex: " + bitIndex + " >= bitsize: " + bitsize);
        }
        super.set(bitIndex);
    }

    @Override
    public void set(int bitIndex, boolean value) {
        if (bitIndex >= bitsize) {
            throw new IndexOutOfBoundsException("bitIndex: " + bitIndex + " >= bitsize: " + bitsize);
        }
        super.set(bitIndex, value);
    }

    @Override
    public void set(int fromIndex, int toIndex) {
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException("fromIndex: " + fromIndex + " > toIndex: " + toIndex);
        }
        if (toIndex >= bitsize) {
            throw new IndexOutOfBoundsException("toIndex: " + toIndex + " > bitsize: " + bitsize);
        }
        super.set(fromIndex, toIndex);
    }

    @Override
    public void set(int fromIndex, int toIndex, boolean value) {
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException("fromIndex: " + fromIndex + " > toIndex: " + toIndex);
        }
        if (toIndex >= bitsize) {
            throw new IndexOutOfBoundsException("toIndex: " + toIndex + " > bitsize: " + bitsize);
        }
        super.set(fromIndex, toIndex, value);
    }

    @Override
    public void clear(int bitIndex) {
        if (bitIndex >= bitsize) {
            throw new IndexOutOfBoundsException("bitIndex: " + bitIndex + " >= bitsize: " + bitsize);
        }
        super.clear(bitIndex);
    }

    @Override
    public void clear(int fromIndex, int toIndex) {
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException("fromIndex: " + fromIndex + " > toIndex: " + toIndex);
        }
        if (toIndex >= bitsize) {
            throw new IndexOutOfBoundsException("toIndex: " + toIndex + " > bitsize: " + bitsize);
        }
        super.clear(fromIndex, toIndex);
    }

    @Override
    public void flip(int bitIndex) {
        if (bitIndex >= bitsize) {
            throw new IndexOutOfBoundsException("bitIndex: " + bitIndex + " >= bitsize: " + bitsize);
        }
        super.flip(bitIndex);
    }

    @Override
    public void flip(int fromIndex, int toIndex) {
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException("fromIndex: " + fromIndex + " > toIndex: " + toIndex);
        }
        if (toIndex >= bitsize) {
            throw new IndexOutOfBoundsException("toIndex: " + toIndex + " > bitsize: " + bitsize);
        }
        super.flip(fromIndex, toIndex);
    }

    @Override
    public int nextSetBit(int fromIndex) {
        if (fromIndex >= bitsize) {
            throw new IndexOutOfBoundsException("fromIndex: " + fromIndex + " >= bitsize: " + bitsize);
        }
        int i = super.nextSetBit(fromIndex);
        return i >= bitsize ? -1 : i;
    }

    @Override
    public int nextClearBit(int fromIndex) {
        if (fromIndex >= bitsize) {
            throw new IndexOutOfBoundsException("fromIndex: " + fromIndex + " >= bitsize: " + bitsize);
        }
        int i = super.nextClearBit(fromIndex);
        return i >= bitsize ? -1 : i;
    }

    @Override
    public void and(BitSet set) {
        if (set.length() > bitsize) {
            throw new IndexOutOfBoundsException("set.length(): " + set.length() + " > bitsize: " + bitsize);
        }
        super.and(set);
    }

    @Override
    public void andNot(BitSet set) {
        if (set.length() > bitsize) {
            throw new IndexOutOfBoundsException("set.length(): " + set.length() + " > bitsize: " + bitsize);
        }
        super.andNot(set);
    }

    @Override
    public void or(BitSet set) {
        if (set.length() > bitsize) {
            throw new IndexOutOfBoundsException("set.length(): " + set.length() + " > bitsize: " + bitsize);
        }
        super.or(set);
    }

    @Override
    public void xor(BitSet set) {
        if (set.length() > bitsize) {
            throw new IndexOutOfBoundsException("set.length(): " + set.length() + " > bitsize: " + bitsize);
        }
        super.xor(set);
    }

    public void add(Bits bits) {
        if (bits.length() > bitsize) {
            throw new IndexOutOfBoundsException("bits.length(): " + bits.length() + " > bitsize: " + bitsize);
        }
        boolean carry = false;
        for (int i = 0; i < bits.length(); i++) {
            boolean a = this.get(i);
            boolean b = bits.get(i);
            boolean sum = a ^ b ^ carry;
            carry = (a && b) || (a && carry) || (b && carry);
            this.set(i, sum);
        }
    }

    /**
     * Converts the bits to an int.
     * can handle negative numbers(2's complement).
     * 
     * @return the int value
     */
    public int toInt() {
        int n = 0;
        for (int i = 0; i < this.length(); i++) {
            n += this.get(i) ? (1 << i) : 0;
        }
        return n;
    }

    /**
     * Converts the bits to a long.
     * 
     * @return the long value
     */
    public long toLong() {
        long l = 0;
        for (int i = 0; i < this.length(); i++) {
            l += this.get(i) ? (1 << i) : 0;
        }
        return l;
    }

    /**
     * Converts the bits to a binary string.
     * 
     * @return the binary string
     */
    public String toBinaryString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.length(); i++) {
            sb.insert(0, this.get(i) ? "1" : "0");
        }
        sb.insert(0, "0b");
        return sb.toString();
    }

    /**
     * Converts the bit set to a hexadecimal string.
     * 
     * @return the hexadecimal string
     */
    public String toHexString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.length(); i += 4) {
            int n = 0;
            for (int j = 0; j < 4; j++) {
                n += this.get(i + j) ? (1 << j) : 0;
            }
            sb.insert(0, Integer.toHexString(n));
        }
        sb.insert(0, "0x");
        return sb.toString();
    }

    /**
     * Converts the int to a Bits object.
     * can handle negative numbers(2's complement).
     * 
     * @param i the int value
     * @return the Bits object
     */
    public static Bits fromInt(int i) {
        Bits bits = new Bits(Integer.SIZE);
        for (int j = 0; j < Integer.SIZE; j++) {
            bits.set(j, (i & (1 << j)) != 0);
        }
        if (bits.length() > bits.bitsize) throw new RuntimeException("the integer is too big to fit in the bits object");
        return bits;
    }

    /**
     * Converts the long to a Bits object.
     * 
     * @param l the long value
     * @return the Bits object
     */
    public static Bits fromLong(long l) {
        Bits bits = new Bits(Long.SIZE);
        for (int i = 0; i < Long.SIZE; i++) {
            bits.set(i, (l & (1 << i)) != 0);
        }
        if (bits.length() > bits.bitsize) throw new RuntimeException("the long is too big to fit in the bits object");
        return bits;
    }

    /**
     * converts bits to binary string.
     */
    @Override
    public String toString() {
        return toBinaryString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Bits) {
            Bits bits = (Bits) obj;
            if (this.length() != bits.length()) {
                return false;
            }
            for (int i = 0; i < this.length(); i++) {
                if (this.get(i) != bits.get(i)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public Bits clone() {
        return (Bits) super.clone();
    }

    /**
     * and operation between two bits objects.
     * 
     * @param a the first bits object
     * @param b the second bits object
     * @return the new bits object that is the result of the operation
     */
    public static Bits and(Bits a, Bits b) {
        if (a.size() != b.size()) {
            throw new IllegalArgumentException("a.size(): " + a.size() + " != b.size(): " + b.size());
        }
        Bits and = new Bits(a.size());
        and.and(a);
        return and;
    }

    /**
     * Adds two bits objects and return new bits object.
     * 
     * @param a the first bits object
     * @param b the second bits object
     * @return the new bits object that is the sum of the two bits objects and have size of the bigger bits object
     */
    public static Bits add(Bits a, Bits b) throws IllegalArgumentException {
        Bits sum = new Bits(Math.max(a.size(), b.size()));
        boolean carry = false;
        for (int i = 0; i < sum.size(); i++) {
            boolean bitA = a.get(i);
            boolean bitB = b.get(i);
            boolean bitSum = bitA ^ bitB ^ carry;
            carry = (bitA && bitB) || (bitA && carry) || (bitB && carry);
            sum.set(i, bitSum);
        }
        if (carry) throw new IllegalArgumentException("the sum is too big to fit in the bits object");
        return sum;
    }
}
