package cci.sort;

import java.util.Arrays;

public class QuickSort {

	public static void main(String[] args) {
		int[] list = { 5, 2, 8, 90, 2, 3, -4, 0, 3, 9 };
		quickSort(list);
		System.out.println(Arrays.toString(list));
		;
	}

	public static final void quickSort(int[] array) {
		if (array == null || array.length < 1) {
			return;
		}

		quickSort(array, 0, array.length-1);
	}

	public static final void quickSort(int[] array, int start, int end) {
		int startPointer = start, endPointer = end;
		int mid = (start + end) >>> 1;
		int pivot = array[mid];
		while (startPointer <= endPointer) {
			while (array[startPointer] < pivot) {
				startPointer++;
			}

			while (array[endPointer] > pivot) {
				endPointer--;
			}

			if (startPointer <= endPointer) {
				swap(array, startPointer++, endPointer--);
			}
		}
		
		if(start < endPointer) {
			quickSort(array, start, endPointer);
		}
		
		if(startPointer < end) {
			quickSort(array, startPointer, end);
		}

	}

	public static final void swap(int[] array, int x, int y) {
		if (x != y) {
			array[x] ^= array[y];
			array[y] ^= array[x];
			array[x] ^= array[y];
		}

	}
}
