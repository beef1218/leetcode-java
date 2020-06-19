package fb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Determine if there exists three elements in a given array that sum to the given target number. Return all the triple of values that sums to target.

Assumptions
The given array is not null and has length of at least 3
No duplicate triples should be returned, order of the values in the tuple does not matter
Examples

A = {1, 2, 2, 3, 2, 4}, target = 8, return [[1, 3, 4], [2, 2, 4]]
 */
/*
1. sort array
2. i < j < k; for each i, do twoSum from i + 1 to end;
3. Pay attention to dedup

Time: O(n^2)
Space: O(n)
 */
public class ThreeSum {
	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		if (nums == null || nums.length < 3) {
			return result;
		}
		Arrays.sort(nums);
		for (int i = 0; i < nums.length - 2; i++) {
			// if there are duplicate numbers, only use the first one and skip the rests
			if (i > 0 && nums[i - 1] == nums[i]) {
				continue;
			}
			twoSum(nums, i, result);
		}
		return result;
	}
	private void twoSum(int[] nums, int prefix, List<List<Integer>> result) {
		int target = -nums[prefix];
		int j = prefix + 1;
		int k = nums.length - 1;
		while (j < k) {
			int sum = nums[j] + nums[k];
			if (sum < target) {
				j++;
			} else if (sum > target) {
				k--;
			} else {
				result.add(Arrays.asList(nums[prefix], nums[j++], nums[k--]));
				// skip the following duplicated numbers
				while (j < k && nums[j - 1] == nums[j]) {
					j++;
				}
			}
		}
	}
}
