package cci.algo.bits;

public class PowerOfTwo {
	
	static final int MAXIMUM_CAPACITY = 1 << 30;
	
	public static void main(String[] args) {
		System.out.println(substractByPowerOfTwo(22));
		System.out.println((int)Math.pow(2, 22));
	}
	
	public static final int substractByPowerOfTwo(int pow) {
		return 1 << pow;
	}
}
