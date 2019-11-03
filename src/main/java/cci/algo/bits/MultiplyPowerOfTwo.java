package cci.algo.bits;

public class MultiplyPowerOfTwo {

	public static void main(String[] args) {
		System.out.println(substractByPowerOfTwo(1, 24));
		System.out.println((int)Math.pow(2, 24));
	}
	
	public static final int substractByPowerOfTwo(int val, int pow) {
		return val << pow;
	}
}
