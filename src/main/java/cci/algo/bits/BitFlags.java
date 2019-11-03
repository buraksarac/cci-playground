package cci.algo.bits;

public class BitFlags {

  private static final int KEYWORD = 01;
  private static final int EXTERNAL = 02;
  private static final int STATIC = 04;
  
  
  public static void main(String[] args) {
    System.out.println(Integer.toBinaryString(KEYWORD));
    System.out.println(Integer.toBinaryString(EXTERNAL));
    System.out.println(Integer.toBinaryString(STATIC));
    int flags = 0;
    flags |= KEYWORD | STATIC | EXTERNAL;
    System.out.println(Integer.toBinaryString(flags));
    flags &= ~(EXTERNAL);
    System.out.println(Integer.toBinaryString(flags));
    flags &= ~(EXTERNAL);
    System.out.println(Integer.toBinaryString(flags));
    flags |= EXTERNAL;
    System.out.println(Integer.toBinaryString(flags));
    flags &= ~(EXTERNAL|STATIC|KEYWORD);
    System.out.println(Integer.toBinaryString(flags));
  }
}
