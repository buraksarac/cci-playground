package cci.sort;

import java.util.Arrays;

public class SelectionSort {

	public static void main(String[] args) {
		int[] list = { 5, 2, 8, 90, 2, 3, -4, 0, 3, 9 };
		selectionSort(list);
		System.out.println(Arrays.toString(list));
		;
	}

	public static final void selectionSort(int[] arr) {

		int start = 0;

		while (start < arr.length) {
			int smindx = start;
			for (int i = start; i < arr.length; i++) {
				if (arr[i] < arr[smindx]) {
					smindx = i;
				}
			}

			if (arr[start] != arr[smindx]) {
				arr[start] ^= arr[smindx];
				arr[smindx] ^= arr[start];
				arr[start] ^= arr[smindx];
			}

			start++;
		}

	}
}
