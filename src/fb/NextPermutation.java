package fb;
/*
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
The replacement must be in-place and use only constant extra memory.
Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.

1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1
 */
/*
1. from right side, find the first pair where nums[i] > nums[i - 1]. elements in [i, end) should be in non-ascending order.
2. if a valid pair is found, swap nums[i-1] with the smallest larger number from [i, end). When there are duplicates, find the one with largest index.
3. sort [i, end) into ascending order in place. Since it's already in non-ascending order, just reverse it in place.
Time: O(n)
Space: O(1)
*/
public class NextPermutation {
	public void nextPermutation(int[] nums) {
		if (nums == null || nums.length <= 1) {
			return;
		}
		int i = nums.length - 1;
		while (i > 0 && nums[i] <= nums[i - 1]) {
			i--;
		}
		// a valid pair is found. i - 1 is the index of number to swap
		if (i > 0) {
			int j = nums.length - 1;
			while (j > i && nums[j] <= nums[i - 1]) {
				j--;
			}
			swap(nums, i - 1, j);
			return;
		}
		reverse(nums, i, nums.length - 1);
	}
	private void reverse(int[] nums, int left, int right) {
		while (left < right) {
			swap(nums, left++, right--);
		}
	}

	// can do binary search to find the smallestLarger number to swap, but it doesn't change the total time complexity
	private int findSmallestLarger(int[] nums, int targetIndex) {
		int left = targetIndex + 1;
		int right = nums.length - 1;
		while (left < right - 1) {
			int mid = left + (right - left) / 2;
			if (nums[mid] <= nums[targetIndex]) {
				right = mid - 1;
			} else {
				left = mid;
			}
		}
		return nums[right] > nums[targetIndex] ? right : left;
	}
	private void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}
}
