package array;

import java.util.Arrays;

/*
Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target.
Return the sum of the three integers. You may assume that each input would have exactly one solution.

Example:
Given array nums = [-1, 2, 1, -4], and target = 1.
The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 */
/*
1. sort
2. i < j < k; fix i, do twoSum with two pointers, find the closest to the target

Time: O(n^2)
Space: O(n)
*/
public class ThreeSumClosest {
	public int threeSumClosest(int[] nums, int target) {
		if (nums == null || nums.length < 3)
			return Integer.MIN_VALUE;

		Arrays.sort(nums);
		int[] result = new int[]{nums[0] + nums[1] + nums[2]};
		for (int i = 0; i < nums.length - 2; i++) {
			twoSumClosest(nums, i, target, result);
		}
		return result[0];
	}

	private void twoSumClosest(int[] nums, int first, int target, int[] result) {
		int i = first + 1;
		int j = nums.length - 1;
		while (i < j) {
			if (Math.abs(target - result[0]) > Math.abs(target - nums[i] - nums[j] - nums[first])) {
				result[0] = nums[i] + nums[j] + nums[first];
			}
			if (nums[i] + nums[j] + nums[first] > target)
				j--;
			else if (nums[i] + nums[j] + nums[first] < target)
				i++;
			else
				return;
		}
	}
}
