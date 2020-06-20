package array;

import java.util.Arrays;
import java.util.Random;

/*
Find the K smallest numbers in an unsorted integer array A. The returned numbers should be in ascending order.
Assumptions:
A is not null
K is >= 0 and smaller than or equal to size of A
 */
/*
1. quick select O(n)
  base case: left == right
  1. quick select with left, right, k
  2. pick a random pivot
  3. move smaller than pivot to its left
  4. if k > pivot index: recursion with left = pivot index; k -= pivot index - left
     if k < pivot index: recursion with right = pivot index
     if k == pivot index: return

2. sort k smallest

Time: quick select: average O(n); sort: k * logk
Space: k
*/
public class KSmallestInUnsortedArray {
	public static void main(String[] args) {
		KSmallestInUnsortedArray solution = new KSmallestInUnsortedArray();
		int[] input = new int[]{11, 12};
		int[] result = solution.kSmallest(input, 2);
		System.out.println(Arrays.toString(result));
	}

	public int[] kSmallest(int[] array, int k) {
		if (array.length == 0 || k == 0)
			return new int[0];

		quickSelect(array, k, 0, array.length - 1);
		int[] result = Arrays.copyOfRange(array, 0, k);
		Arrays.sort(result);
		return result;
	}

	private void quickSelect(int[] array, int k, int left, int right) {
		if (left == right)
			return;

		int pivot = partition(array, left, right);
		int num = pivot - left;
		if (num < k) {
			quickSelect(array, k - num, pivot, right);
		} else if (num > k) {
			quickSelect(array, k, left, pivot);
		}
	}

	private int partition(int[] array, int left, int right) {
		int pivot = new Random().nextInt(right - left + 1) + left;
		swap(array, pivot, right);
		int i = left;
		int j = right - 1;
		while (i <= j) {
			if (array[i] < array[right]) {
				i++;
			} else {
				swap(array, i, j--);
			}
		}
		swap(array, right, i);
		return i;
	}

	private void swap(int[] array, int i, int j) {
		int tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}
}
