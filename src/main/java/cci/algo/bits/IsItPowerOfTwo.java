package cci.algo.bits;

public class IsItPowerOfTwo {

	public static void main(String[] args) {
		System.out.println(isItPowerOfTwo(16));
	}
	public static final boolean isItPowerOfTwo(int val) {
		return (val & (val -1)) == 0;
	}
}
