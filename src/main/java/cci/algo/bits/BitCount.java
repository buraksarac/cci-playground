package cci.algo.bits;

import java.util.stream.IntStream;

public class BitCount {

  public static void main(String[] args) {
    IntStream.range(13, 14).forEach(BitCount::validate);
    System.out.println("Passed!");
    
  }
  
  private static final void validate(int i) {
    if(Integer.bitCount(i) != bitCount(i)) {
      throw new RuntimeException("bzz");
    }
  }

  private static final int bitCount(int val) {
    int x = 0;
    
    do {
      System.out.println("Current " + Integer.toBinaryString(val));
      x++;
    } while ((val &= (val - 1)) > 0);
    return x;
  }
}
