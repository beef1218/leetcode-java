package fb;

import java.util.HashMap;
import java.util.Map;

/*
Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

Example 1:
Input:nums = [1,1,1], k = 2
Output: 2
 */
public class SubarraySumEqualsK {
	/*
	Method1: Using a prefixSum array
	Time: n ^ 2
	Space: n
	 */
	public int subarraySum1(int[] nums, int k) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int[] prefixSum = new int[nums.length];
		prefixSum[0] = nums[0];
		for (int i = 1; i < nums.length; i++) {
			prefixSum[i] = prefixSum[i - 1] + nums[i];
		}
		int count = 0;
		for (int i = 0; i < prefixSum.length; i++) {
			for (int j = i; j < prefixSum.length; j++) {
				if (prefixSum[j] - prefixSum[i] + nums[i] == k) {
					count++;
				}
			}
		}
		return count;
	}

	/*
	Method2: brute force, but keep updating current sum when moving end pointer
	Time: n ^ 2
	Space: 1
	 */
	public int subarraySum2(int[] nums, int k) {
		int count = 0;
		for (int i = 0; i < nums.length; i++) {
			int sum = 0;
			for (int j = i; j < nums.length; j++) {
				sum += nums[j];
				if (sum == k) {
					count++;
				}
			}
		}
		return count;
	}

	/*
	Method3: Use a hashmap to store <prefixSum : number of times this sum appeared>
	for each prefixSum, the number of times prefixSum - k appeared is the number of subarrays whose sum is k
	Time: n
	Space: n
	 */
	public int subarraySum3(int[] nums, int k) {
		Map<Integer, Integer> map = new HashMap<>();
		map.put(0, 1);
		int count = 0;
		int sum = 0;
		for (int num : nums) {
			sum += num;
			if (map.containsKey(sum - k)) {
				count += map.get(sum - k);
			}
			map.put(sum, map.getOrDefault(sum, 0) + 1);
		}
		return count;
	}
}
