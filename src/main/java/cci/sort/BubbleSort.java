package cci.sort;

import java.util.Arrays;

public class BubbleSort {
	
	public static void main(String[] args) {
		int[] list = {5,2,8,90,2,3,-4,0,3,9};
		bubbleSort(list);
		System.out.println(Arrays.toString(list));;
	}

	private static final void bubbleSort(int[] arr) {

		boolean fullySorted = false;
		while(!fullySorted) {
			fullySorted = true;
			for (int current = arr.length - 1, next = current - 1; current >= 1; current--, next--) {
				if (arr[current] < arr[next]) {
					arr[current] ^= arr[next];
					arr[next] ^= arr[current];
					arr[current] ^= arr[next];
					fullySorted = false;
				}
			}
		}
		

	}
}
