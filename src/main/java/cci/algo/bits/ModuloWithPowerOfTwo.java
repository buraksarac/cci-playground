package cci.algo.bits;

public class ModuloWithPowerOfTwo {
	
	public static void main(String[] args) {
		System.out.println(modPowerTwo(39, 5));
	}

	public static final int modPowerTwo(int val, int pow) {
		return val & ((1 << pow) - 1) ;
	}
}
