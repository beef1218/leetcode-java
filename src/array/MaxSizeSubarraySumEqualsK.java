package array;

import java.util.HashMap;
import java.util.Map;
/*
1. use a hashmap to store prefix sum : index of the first appearance of this sum
2. linear scan, put number into the map, and check whether num - target exist. keep updating global max

Time: O(n)
Space: O(n)
*/
public class MaxSizeSubarraySumEqualsK {
	public int maxSubArrayLen(int[] nums, int k) {
		if (nums == null || nums.length == 0)
			return 0;

		Map<Integer, Integer> map = new HashMap<>();
		map.put(0, -1); // need this to cover the whole prefix sum == target scenario
		int max = 0;
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			if (map.containsKey(sum - k)) {
				max = Math.max(max, i - map.get(sum - k));
			}
			if (!map.containsKey(sum))
				map.put(sum, i);
		}
		return max;
	}
}
