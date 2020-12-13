package fb;

/*
Given a non-negative integer, you could swap two digits at most once to get the maximum valued number. Return the maximum valued number you could get.

Example 1:
Input: 2736
Output: 7236
Explanation: Swap the number 2 and the number 7.
Example 2:
Input: 9973
Output: 9973
Explanation: No swap.
 */
public class MaximumSwap {
	/*
	from left to right, if for a number, there is another larger(largest) number to its right, we need to do this swap
	build a prefixMax array from right to left, store the index instead of value
	linear scan:
	    for each number, if its prefixSum is greater, do the swap
	*/
	public int maximumSwap(int num) {
		if (num < 10) {
			return num;
		}
		char[] array = String.valueOf(num).toCharArray();
		int[] prefixMax = new int[array.length];
		prefixMax[prefixMax.length - 2] = array.length - 1;
		for (int i = prefixMax.length - 3; i >= 0; i--) {
			prefixMax[i] = array[i + 1] > array[prefixMax[i + 1]] ? i + 1 : prefixMax[i + 1];
		}
		for (int i = 0; i < array.length - 1; i++) {
			if (array[i] < array[prefixMax[i]]) {
				swap(array, i, prefixMax[i]);
				break;
			}
		}
		return Integer.parseInt(new String(array));
	}

	private void swap(char[] array, int i, int j) {
		char tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}

	/*
	Greedy:
	use a int[10] to store the last index of each digit
	scan from left to right, for each digit, if there is larger digit whose last index is greater than current index, swap it
	 */
	public int maximumSwapGreedy(int num) {
		char[] array = String.valueOf(num).toCharArray();
		int[] lastIndex = new int[10];
		for (int i = 0; i < array.length; i++) {
			lastIndex[array[i] - '0'] = i;
		}
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = 9; j > array[i] - '0'; j--) {
				if (i < lastIndex[j]) {
					swap(array, i, lastIndex[j]);
					return Integer.parseInt(new String(array));
				}
			}
		}
		return num;
	}
}
