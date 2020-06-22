package array;

/*
Given a sorted array A of unique numbers, find the K-th missing number starting from the leftmost number of the array.

Example 1:
Input: A = [4,7,9,10], K = 1
Output: 5
Explanation:
The first missing number is 5.

Example 2:
Input: A = [4,7,9,10], K = 3
Output: 8
Explanation:
The missing numbers are [5,6,8,...], hence the third missing number is 8.
 */
/*
1. check number of missing number before end of array (count). if count < k, return last element + k - count
do binary search:
1. get mid
2. check number of missing number before mid (count)
if count < k: left = mid
if count >= k: right = mid
exit loop when there are two elements left. The target missing is between them.
return nums[left] + k
*/
public class MissingElementInSortedArray {
	public int missingElement(int[] nums, int k) {
		int totalMissing = getMissingCount(nums, nums.length - 1);
		if (k > totalMissing) {
			return nums[nums.length - 1] + k - totalMissing;
		}
		int left = 0;
		int right = nums.length - 1;
		while (left < right - 1) {
			int mid = left + (right - left) / 2;
			int count = getMissingCount(nums, mid);
			if (count < k) {
				left = mid;
			} else {
				right = mid;
			}
		}
		return nums[left] + k - getMissingCount(nums, left);
	}

	private int getMissingCount(int[] nums, int index) {
		return nums[index] - nums[0] - index;
	}
}
