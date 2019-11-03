package cci.str;

public class Atoi {

	private static final int[] INT_LOOKUP = new int[] { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57 };

	public static void main(String[] args) {
		String str = "0123456789";
		
		System.out.println(atoi(str));

	}

	private static final int atoi(String str) {
		if(str == null || str.length() < 1) {
			throw new IllegalArgumentException();
		}
		char[] chars = str.toCharArray();

		long decimalPlace = 1;
		int val = 0;
		for (int i = chars.length - 1; i >= 0; i--) {
			val += decimalPlace * charToInt(chars[i]);
			decimalPlace = (decimalPlace << 1) + (decimalPlace <<3);
		}
		
		return val;
	}

	private static final int charToInt(char c) {
		
		for (int i = 0; i < INT_LOOKUP.length; i++) {
			if ((int) c == INT_LOOKUP[i]) {
				return i;
			}

		}
		throw new IllegalArgumentException("'" + c + "' Unrecognized char type");
	}

}
