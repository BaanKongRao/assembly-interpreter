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
            throw new IndexOutOfBoundsException("The bit only has [0, " + bitsize + ") bits but get(" + bitIndex + ")");
        }
        return super.get(bitIndex);
    }

    @Override
    public Bits get(int fromIndex, int toIndex) {
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException("fromIndex: " + fromIndex + " > toIndex: " + toIndex);
        }
        if (toIndex >= bitsize) {
            throw new IndexOutOfBoundsException("The bit only has [0, " + bitsize + ") bits but get(" + fromIndex + ", " + toIndex + ")");
        }
        return (Bits) super.get(fromIndex, toIndex);
    }

    @Override
    public void set(int bitIndex) {
        if (bitIndex >= bitsize) {
            throw new IndexOutOfBoundsException("The bit only has [0, " + bitsize + ") bits but set(" + bitIndex + ")");
        }
        super.set(bitIndex);
    }

    @Override
    public void set(int bitIndex, boolean value) {
        if (bitIndex >= bitsize) {
            throw new IndexOutOfBoundsException("The bit only has [0, " + bitsize + ") bits but set(" + bitIndex + ", " + value + ")");
        }
        super.set(bitIndex, value);
    }

    @Override
    public void set(int fromIndex, int toIndex) {
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException("fromIndex: " + fromIndex + " > toIndex: " + toIndex);
        }
        if (toIndex >= bitsize) {
            throw new IndexOutOfBoundsException("The bit only has [0, " + bitsize + ") bits but set(" + fromIndex + ", " + toIndex + ")");
        }
        super.set(fromIndex, toIndex);
    }

    @Override
    public void set(int fromIndex, int toIndex, boolean value) {
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException("fromIndex: " + fromIndex + " > toIndex: " + toIndex);
        }
        if (toIndex >= bitsize) {
            throw new IndexOutOfBoundsException("The bit only has [0, " + bitsize + ") bits but set(" + fromIndex + ", " + toIndex + ", " + value + ")");
        }
        super.set(fromIndex, toIndex, value);
    }

    @Override
    public void clear(int bitIndex) {
        if (bitIndex >= bitsize) {
            throw new IndexOutOfBoundsException("The bit only has [0, " + bitsize + ") bits but clear(" + bitIndex + ")");
        }
        super.clear(bitIndex);
    }

    @Override
    public void clear(int fromIndex, int toIndex) {
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException("fromIndex: " + fromIndex + " > toIndex: " + toIndex);
        }
        if (toIndex >= bitsize) {
            throw new IndexOutOfBoundsException("The bit only has [0, " + bitsize + ") bits but clear(" + fromIndex + ", " + toIndex + ")");
        }
        super.clear(fromIndex, toIndex);
    }

    @Override
    public void flip(int bitIndex) {
        if (bitIndex >= bitsize) {
            throw new IndexOutOfBoundsException("The bit only has [0, " + bitsize + ") bits but flip(" + bitIndex + ")");
        }
        super.flip(bitIndex);
    }

    @Override
    public void flip(int fromIndex, int toIndex) {
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException("fromIndex: " + fromIndex + " > toIndex: " + toIndex);
        }
        if (toIndex > bitsize) {
            throw new IndexOutOfBoundsException("The bit only has [0, " + bitsize + "] bits but flip(" + fromIndex + ", " + toIndex + ")");
        }
        super.flip(fromIndex, toIndex);
    }

    @Override
    public int nextSetBit(int fromIndex) {
        if (fromIndex >= bitsize) {
            throw new IndexOutOfBoundsException("The bit only has [0, " + bitsize + ") bits but nextSetBit(" + fromIndex + ")");
        }
        int i = super.nextSetBit(fromIndex);
        return i >= bitsize ? -1 : i;
    }

    @Override
    public int nextClearBit(int fromIndex) {
        if (fromIndex >= bitsize) {
            throw new IndexOutOfBoundsException("The bit only has [0, " + bitsize + ") bits but nextClearBit(" + fromIndex + ")");
        }
        int i = super.nextClearBit(fromIndex);
        return i >= bitsize ? -1 : i;
    }

    public void add(Bits bits) {
        boolean carry = false;
        for (int i = 0; i < bits.size(); i++) {
            boolean bitA = this.get(i);
            boolean bitB = bits.get(i);
            boolean bitSum = bitA ^ bitB ^ carry;
            carry = (bitA && bitB) || (bitA && carry) || (bitB && carry);
            this.set(i, bitSum);
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
        for (int i = 0; i < this.size(); i++) {
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
        for (int i = 0; i < this.size(); i++) {
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
        for (int i = 0; i < this.size(); i++) {
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
        for (int i = 0; i < this.size(); i += 4) {
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
            if (this.size() != bits.size()) {
                return false;
            }
            for (int i = 0; i < this.size(); i++) {
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
     * @return the new bits object size of the longer bits object
     */
    public static Bits and(Bits a, Bits b) {
        Bits result = a.size() > b.size() ? a.clone() : b.clone();
        result.and(a.size() > b.size() ? b : a);
        return result;
    }

    /**
     * Adds two bits objects and return new bits object.
     * 
     * IMPORTANT: the negative must be second parameter.
     * 
     * @param a the first bits object
     * @param b the second bits object
     * @return the new bits object that is the sum of the two bits objects and have size of the bigger bits object
     */
    public static Bits add(Bits a, Bits b) {
        Bits result = a.size() > b.size() ? a.clone() : b.clone();
        result.add(a.size() > b.size() ? b : a);
        return result;
    }
}
