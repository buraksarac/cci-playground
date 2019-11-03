package cci.sort;

import java.util.Arrays;

public class InsertionSort {

	public static void main(String[] args) {
		int[] list = { 5, 2, 8, 90, 2, 3, -4, 0, 3, 9 };
		insertionSort(list);
		System.out.println(Arrays.toString(list));
		;
	}
	
	public static final void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; ++i) { 
        	System.out.println(Arrays.toString(arr));
    		;
            int key = arr[i]; 
            int j = i - 1; 
            while (j >= 0 && arr[j] > key) { 
                arr[j + 1] = arr[j--]; 
            } 
            arr[j + 1] = key; 
        } 
        
	}
}
