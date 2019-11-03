package cci.algo;

public class BinarySearch {
	
	public static void main(String[] args) {
		int[] list = new int[] {1,2,3,4,5,6,7,8,9,10};
		System.out.println(binarySearch(list, 4));
	}

	public static int binarySearch(int[] list, int key) {
		int low = 0;
		int high = list.length - 1;
		
		while(low <= high) {
			int mid = (low + high) >>> 1;
			int midVal = list[mid];
			if(midVal > key) {
				high = mid - 1;
			}else if(midVal < key) {
				low = mid + 1;
			}else {
				return mid;
			}
		}
		
		return -(low + 1);  // key not found.
		
	}
}
