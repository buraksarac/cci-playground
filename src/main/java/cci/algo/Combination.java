package cci.algo;

/**
 * When the order doesn't matter, it is a Combination.
 *  Combination without
 * repetition: n!/r!(n-r)!
 * 
 * with repetiotion: (r+n-1)! / r!(n-1)!
 * 
 * TODO: BigInteger
 *
 */
public class Combination {
	
	public static void main(String[] args) {

		System.out.println(withOutRepetition1(16, 3));
		System.out.println(withRepetition1(16, 3));
	}

	public static long withOutRepetition1(long pool, long choices) {
		long limit = pool - choices;
		long val = 1;
		long division = 1;
		for (long i = pool, r=1; i > limit; i--,r++) {
			val *= i;
			division *= r;
		}
		return val / division;

	}
	
	public static long withRepetition1(long pool, long choices) {
		long start = pool + choices - 1;
		long limit = pool -1;
		
		long val = 1;
		long division = 1;
		for (long i = start, r=1; i > limit; i--,r++) {
			val *= i;
			division *= r;
		}
		return val / division;

	}
}
