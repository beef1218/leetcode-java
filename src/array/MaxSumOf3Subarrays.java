package array;

import java.util.Arrays;

/*
In a given array nums of positive integers, find three non-overlapping subarrays with maximum sum.
Each subarray will be of size k, and we want to maximize the sum of all 3*k entries.
Return the result as a list of indices representing the starting position of each interval (0-indexed). If there are multiple answers, return the lexicographically smallest one.

Example:
Input: [1,2,1,2,6,7,5,1], 2
Output: [0, 3, 5]
Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.
 */
/*
1. get sum of each window: array of sums
2. get prefixMax left and right: store the index of max sum from left and from right.
   We need to store the index instead of the value because we need to keep track of the index to use as result
3. for each middle window, get total by adding middle window sum, left max sum, and right max sum. update global max

time: O(n)
space: O(n)
*/
public class MaxSumOf3Subarrays {
	public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
		if (nums == null || nums.length <= 0 || nums.length < k * 3 || k <= 0)
			return new int[0];

		int[] sums = getWindowSum(nums, k);
		int[] left = getLeftOrRightMaxIndex(sums, true);
		int[] right = getLeftOrRightMaxIndex(sums, false);
		int[] result = new int[]{-1, -1, -1};
		for (int i = k; i < sums.length - k; i++) {
			if (result[0] == -1 || sums[i] + sums[left[i - k]] + sums[right[i + k]] > sums[result[0]] + sums[result[1]] + sums[result[2]]) {
				result[0] = left[i - k];
				result[1] = i;
				result[2] = right[i + k];
			}
		}
		return result;
	}

	private int[] getWindowSum(int[] nums, int k) {
		int[] sumArr = new int[nums.length - k + 1];
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			if (i >= k)
				sum -= nums[i - k];

			if (i >= k - 1)
				sumArr[i - k + 1] = sum;
		}
		return sumArr;
	}

	private int[] getLeftOrRightMaxIndex(int[] sums, boolean isLeft) {
		int[] result = new int[sums.length];
		if (isLeft) {
			for (int i = 1; i < sums.length; i++) {
				result[i] = sums[i] > sums[result[i - 1]] ? i : result[i - 1];
			}
		} else {
			result[result.length - 1] = result.length - 1;
			for (int i = sums.length - 2; i >= 0; i--) {
				result[i] = sums[i] >= sums[result[i + 1]] ? i : result[i + 1];
			}
		}
		return result;
	}
}
