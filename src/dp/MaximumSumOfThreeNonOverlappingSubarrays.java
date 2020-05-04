package dp;

import java.util.Arrays;

public class MaximumSumOfThreeNonOverlappingSubarrays {
	public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
		if (nums == null || nums.length <= 0 || nums.length < k * 3 || k <= 0)
			return new int[0];

		int[] sums = getWindowSum(nums, k);
		int[] left = getLeftOrRightMaxIndex(sums, true);
		int[] right = getLeftOrRightMaxIndex(sums, false);
		int[] result = new int[3];
		Arrays.fill(result, -1);
		for (int i = k; i < sums.length - k; i++) {
			if (result[0] == -1 || sums[i] + sums[left[i - k]] + sums[right[i + k]] > sums[result[0]] + sums[result[1]]
					+ sums[result[2]]) {
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
