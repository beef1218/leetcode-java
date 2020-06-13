package array;

/*
Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Example:

Input: [0,1,0,3,12]
Output: [1,3,12,0,0]
Note:

You must do this in-place without making a copy of the array.
Minimize the total number of operations.
 */
/*
1. use two pointers start from left. slow: [begin, slow) are non-zero numbers. fast: a current pointer
2. if nums[fast] is zero: fast++
   else: nums[slow++] = nums[fast++]
3. when fast reaches the end, fill zeros into [slow, end]
*/
public class MoveZeros {
	public void moveZeroes(int[] nums) {
		if (nums == null || nums.length == 0) {
			return;
		}
		int slow = 0;
		for (int fast = 0; fast < nums.length; fast++) {
			if (nums[fast] != 0) {
				nums[slow++] = nums[fast];
			}
		}
		while (slow < nums.length) {
			nums[slow++] = 0;
		}
	}
}
