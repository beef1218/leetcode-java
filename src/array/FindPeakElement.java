package array;

/*
A peak element is an element that is greater than its neighbors.
Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.
The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that nums[-1] = nums[n] = -∞.
 */
/*
binary search:
1. if mid > both neighbors, mid is the peak
2. if mid < left neighbor, right = mid - 1
3. if mid < right neighbor, left = mid + 1
4. post processing
*/
public class FindPeakElement {
	public int findPeakElement(int[] nums) {
		//assume nums is not null and not empty
		int left = 0;
		int right = nums.length - 1;
		while (left < right - 1) {
			int mid = left + (right - left) / 2;
			if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
				return mid;
			} else if (nums[mid] < nums[mid - 1]) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return nums[left] > nums[right] ? left : right;
	}
}
