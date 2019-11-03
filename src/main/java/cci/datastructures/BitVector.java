package cci.datastructures;


public class BitVector {

    private long[] bucket;
    private int size;

    public BitVector(int size) {
        int count = ((size - (size & 63)) / 64) + 1;
        this.bucket = new long[count];
        this.size = size;
    }

    public boolean get(int position) {
        return (bucket[position >> 6] & (1L << (position & (63)))) != 0;
    }

    public void flip(int position) {
        bucket[position >> 6] = (bucket[position >> 6] ^ (1L << (position & (63))));
    }
    public void onOff(int position, boolean on) {
        bucket[position >> 6] = (bucket[position >> 6] & (~(1L << (position & (63)))))
                | (on ? 1 : 0 << position & (63));
    }

    public void on(int position) {
        bucket[position >> 6] = (bucket[position >> 6] | (1L << (position & (63))));
    }

    public void off(int position) {
        bucket[position >> 6] = (bucket[position >> 6] & ~(1L << (position & (63))));
    }

    public int getSize() {
        return size;
    }
    
    public long[] getBucket() {
        return bucket;
    }
    
}
