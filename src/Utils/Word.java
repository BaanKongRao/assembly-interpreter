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
     * make it into decimal number to show on terminal for testing
     */
    public String toDecString(){
        int machineCode = 0;
        for(int i = 0; i <= 24; i++){
            if(this.get(i) == true) machineCode += Math.pow(2, i);
        }
        String sb = String.valueOf(machineCode);
        return sb;
    }

    @Override
    public Word clone() {
        return (Word) super.clone();
    }

    /**
     * Use to tranform No. of registers into 3 binary bits
     */
    public void regToWord(Integer reg, Integer MSBbit){
        String binReg = String.format("%3s", Integer.toBinaryString(reg)).replace(' ', '0');
        for(int i = 0; i < 3; i++){
            if(binReg.charAt(i) == '1'){
                this.set(MSBbit-i, true);
            }
        }
    }

    /**
     * Use to tranform offsetfield into 16 binary bits
     */
    public void offsetToWord(Integer offsetfield){
        String offsetbits = twoComplement(offsetfield);
        for (int i = 0; i < 16; i++) {
            if(offsetbits.charAt(i) == '1') this.set(15-i);
        }
    }

    /*
     * use to trasform offsetField by two complement function
     */
    private String twoComplement(Integer labelAddress){
        if(labelAddress >= 0){//positive number case
            String binary16bits = String.format("%16s", Integer.toBinaryString(Math.abs(labelAddress))).replace(' ', '0');
            return binary16bits;
        }else{//negative number case, use two complement
            
            //1. absolute into positive and make binary
            String absolutebit = String.format("%16s", Integer.toBinaryString(Math.abs(labelAddress))).replace(' ', '0');
            int decimalTransitBits = 0;
            //2. flip all bit, and make it into decimal (not accurate value because it isn't two complement system)
            //but it makes easy to plus 1
            for(int i = 0; i < 16; i++){
                if(absolutebit.charAt(i) == '0'){
                    decimalTransitBits += Math.pow(2, 15-i);
                }
            }
            decimalTransitBits++; //3. plus 1 at last bit
            //4. change back into binary we will get two complement bits
            String finalbit = String.format("%16s", Integer.toBinaryString(decimalTransitBits)).replace(' ', '1');
            return finalbit;
        }
    }

    /**
     * Performs bitwise AND operation on two words.
     * 
     * @param word1 the first word
     * @param word2 the second word
     * @return the new word that is the result of the operation
     */
    public static Word and(Word word1, Word word2) {
        return (Word) Bits.and(word1, word2);
    }

    /**
     * Adds two words.
     * 
     * @param word1 the first word
     * @param word2 the second word
     * @return the sum
     */
    public static Word add(Word word1, Word word2) {
        return (Word) Bits.add(word1, word2);
    }

}
