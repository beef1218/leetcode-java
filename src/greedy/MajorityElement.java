package greedy;

/*
Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
You may assume that the array is non-empty and the majority element always exist in the array.

Example 1:
Input: [3,2,3]
Output: 3

Example 2:
Input: [2,2,1,1,1,2,2]
Output: 2
 */
public class MajorityElement {
	public int majorityElement(int[] nums) {
		if (nums == null || nums.length == 0) {
			return Integer.MIN_VALUE;
		}
		int count = 1;
		int cur = nums[0];
		for (int i = 1; i < nums.length; i++) {
			if (count == 0) {
				count++;
				cur = nums[i];
			} else {
				if (nums[i] == cur) {
					count++;
				} else {
					count--;
				}
			}
		}
		return cur;
	}
}
