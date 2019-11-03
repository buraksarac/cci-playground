package cci.algo.bits;

public class DivideByPowerOfTwo {

	public static void main(String[] args) {
		System.out.println(divideByPowerOfTwo(16777216, 24));
		System.out.println(divideByPowerOfTwo(210, 6));
	}
	
	
	public static final int divideByPowerOfTwo(int val, int pow) {
		return val >> pow;
	}
}
