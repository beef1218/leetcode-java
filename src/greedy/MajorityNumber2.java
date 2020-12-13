package greedy;

import java.util.ArrayList;
import java.util.List;

/*
Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
Note: The algorithm should run in linear time and in O(1) space.

Example 1:
Input: [3,2,3]
Output: [3]

Example 2:
Input: [1,1,1,3,3,2,2,2]
Output: [1,2]
 */
/*
num1, num2, count1, count2
if num != num1 && num != num2, reduce both counters by 1
*/
public class MajorityNumber2 {
	public List<Integer> majorityElement(int[] nums) {
		List<Integer> result = new ArrayList<>();
		if (nums == null || nums.length == 0) {
			return new ArrayList<>();
		}
		int cur1 = Integer.MIN_VALUE;
		int count1 = 0;
		int cur2 = Integer.MIN_VALUE;
		int count2 = 0;
		for (int num : nums) {
			if (num == cur1) {
				count1++;
			} else if (num == cur2) {
				count2++;
			} else if (count1 == 0) {
				cur1 = num;
				count1++;
			} else if (count2 == 0) {
				cur2 = num;
				count2++;
			} else {
				count1--;
				count2--;
			}
		}
		count1 = 0;
		for (int num : nums) {
			if (num == cur1) {
				count1++;
			}
		}
		if (count1 > nums.length / 3) {
			result.add(cur1);
		}
		if (cur2 != Integer.MIN_VALUE) {
			count2 = 0;
			for (int num : nums) {
				if (num == cur2) {
					count2++;
				}
			}
			if (count2 > nums.length / 3) {
				result.add(cur2);
			}
		}
		return result;
	}
}
