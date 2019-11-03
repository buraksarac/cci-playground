package cci.sort;

import java.util.Arrays;

public class Merge {
	public static void main(String... args) {
		int[] list = { 5, 2, 8, 90, 2, 3, -4, 0, 3, 9 };
		mergeSort(list);
		System.out.println(Arrays.toString(list));
	}

	public static final void mergeSort(int[] array) {
		if (array == null || array.length < 1) {
			return;
		}
		int[] tmp = new int[array.length];
		mergeSort(array, tmp, 0, array.length - 1);
	}

	public static final void mergeSort(int[] array, int[] tmp, int low, int high) {
		if (low < high) {
			int mid = (low + high) >>> 1;
			mergeSort(array, tmp, low, mid); // left side of given list
			mergeSort(array, tmp, mid + 1, high); // right side of given list
			merge(array, tmp, low, mid, high); // actual merge operation
		}
	}

	private static final void merge(int[] array, int[] tmp, int low, int mid, int high) {
		for (int i = low; i <= high; i++) {
			tmp[i] = array[i];
		}

		int left = low;
		int right = mid + 1;
		int current = low;

		while (left <= mid && right <= high) {
			if (tmp[left] <= tmp[right]) {
				array[current] = tmp[left++];
			} else {
				array[current] = tmp[right++];
			}
			current++;
		}

		int remaining = mid - left;
		for (int i = 0; i <= remaining; i++) {
			array[current + i] = tmp[left + i];
		}
	}
}
