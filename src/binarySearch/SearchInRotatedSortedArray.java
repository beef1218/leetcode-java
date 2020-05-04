package binarySearch;

public class SearchInRotatedSortedArray {
	/*
	 * 4 5 6 7 8 9 1 2 3
	 * 
	 * 1. get mid. if mid == target, return mid. 
	 * 2a. if mid >= left: rotate point is on right 
	 * 			if left <= target < mid, right = mid - 1 
	 * 			else, left = mid + 1 
	 * 2b. else: rotate point is on left 
	 * 			if mid < target <= right, left = mid + 1 
	 * 			else, right = mid - 1
	 * 
	 * 
	 */
	public int search(int[] nums, int target) {
		if (nums == null || nums.length == 0)
			return -1;

		int left = 0;
		int right = nums.length - 1;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (nums[mid] == target)
				return mid;

			if (nums[left] <= nums[mid]) { // rotate point is on the right
				if (nums[left] <= target && nums[mid] > target)
					right = mid - 1;
				else
					left = mid + 1;
			} else { // rotate point is on the left
				if (nums[mid] < target && target <= nums[right])
					left = mid + 1;
				else
					right = mid - 1;
			}
		}
		return -1;
	}
}
