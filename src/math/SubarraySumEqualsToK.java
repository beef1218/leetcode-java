package math;

import java.util.HashMap;
import java.util.Map;

/*
Given a list of non-negative numbers and a target integer k, write a function to check if the array has a continuous subarray of size at least 2 that sums up to a multiple of k, that is, sums up to n*k where n is also an integer.

Example 1:
Input: [23, 2, 4, 6, 7],  k=6
Output: True
Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.

Example 2:
Input: [23, 2, 6, 4, 7],  k=6
Output: True
Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
 */
public class SubarraySumEqualsToK {
	/*
	Method 1: Use prefixSum array and nested for loop.
	1. build prefixSum array
	2. use nested for loop to check all subarrays sum with length of at least 2
	Time: O(n ^ 2)
	Space: O(n)
	*/
	public boolean checkSubarraySum(int[] nums, int k) {
		if (nums == null || nums.length <= 1) {
			return false;
		}
		int[] prefixSum = new int[nums.length];
		prefixSum[0] = nums[0];
		for (int i = 1; i < nums.length; i++) {
			prefixSum[i] = prefixSum[i - 1] + nums[i];
		}
		for (int i = 0; i < nums.length - 1; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				int sum = prefixSum[j] - prefixSum[i] + nums[i];
				if (sum == k || k != 0 && sum % k == 0) {
					return true;
				}
			}
		}
		return false;
	}

	/*
	Method2:
	Math: for the prefixSum,  if a % k = x; b % k = x, then a % k - b % k = (a - b) % k == 0. The sum from [a+1, b] is multiple of k
	1. linear scan to calculate the sum from start to i
	2. use a hashmap to store <sum % k, index>
	3. if a sum % k already exists in the map, and the current index - the previous index > min length, we have a valid result
	4. need to handle special case where the sum itself is multiple of k
	Time: O(n)
	Space: min(k, n)
	 */
	public boolean checkSubarraySum2(int[] nums, int k) {
		Map<Integer, Integer> map = new HashMap<>();
		int sum = 0;
		map.put(0, -1); // to cover the case where sum of subarray itself is multiple of k
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			if (k != 0) {
				sum %= k;
			}
			if (!map.containsKey(sum)) {
				map.put(sum, i);
			} else {
				if (i - map.get(sum) >= 2) { // in this case the subarray length needs to be at least 2
					return true;
				}
			}
		}
		return false;
	}
}
