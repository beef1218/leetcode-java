package math;

/*
Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
 */
/*
array length: n. it contains 0, 1, 2, ..., n (missing one of them)
XOR from 0 to n, then XOR each number, we will have the missing number left
*/
public class MissingNumber {
	public int missingNumber(int[] nums) {
		int num = nums.length;
		for (int i = 0; i < nums.length; i++) {
			num ^= i ^ nums[i];
		}

		return num;
	}
}
