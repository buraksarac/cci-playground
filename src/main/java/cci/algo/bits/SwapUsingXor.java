package cci.algo.bits;

public class SwapUsingXor {

  public static final void swap(int[] array, int x, int y) {
    if (x != y) {
        array[x] ^= array[y];
        array[y] ^= array[x];
        array[x] ^= array[y];
    }

}
}
