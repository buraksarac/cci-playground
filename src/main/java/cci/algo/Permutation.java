package cci.algo;

/*
 * When the order does matter it is a Permutation.
 * With repetition = 3 perms of 16 = 3pow16
 * without repetition = 3 perms of 16  n!/(n-1)! which is 16*15*14
 * 
 * TODO: BigInteger
 */
public class Permutation {
	
	/**
	 *   16!	 = 	16!	 = 	20,922,789,888,000	 = 3,360
	 *  -----      ----   --------------------------
     *  (16-3)!	    13!     6,227,020,800
     *  (which is just the same as: 16 × 15 × 14 = 3,360)
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(permWithoutRepetition(16, 3));
	}

	public static long permWithoutRepetition(long pool, long choices) {
		long limit = pool - choices;
		long val = 1;
		for (long i = pool; i > limit; i--) {
			val *= i;
		}
		return val;
	}
}
