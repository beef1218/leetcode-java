package array;

import java.util.Random;

/*
Finding Kth largest is equivalent to finding n - k smallest element (convert to index that starts from 0)
QuickSelect:
1. in range of [left, right], do partition
	1. find a random pivot
	2. move elements smaller than pivot to the left of pivot
if pivot == k: return nums[pivot]
if pivot < k: repeat in range [pivot + 1, right]
if pivot > k: repeat in rage [left, pivot - 1]
base case in recursion: if left == right, return this single element

Time: average O(n), worse case O(n ^ 2)
Space: average O(logn), worse case O(n)
*/
public class KthLargestInUnsortedArray {
	public int findKthLargest(int[] nums, int k) {
		k = nums.length - k;
		return helper(nums, 0, nums.length - 1, k);
	}
	private int helper(int[] nums, int left, int right, int k) {
		if (left == right) {
			return nums[left];
		}
		int pivot = partition(nums, left, right);
		if (pivot == k) {
			return nums[pivot];
		} else if (pivot < k) {
			return helper(nums, pivot + 1, right, k);
		} else {
			return helper(nums, left, pivot - 1, k);
		}
	}
	private int partition(int[] nums, int left, int right) {
		int pivot = new Random().nextInt(right - left + 1) + left;
		swap(nums, pivot, right);
		int i = left;
		int j = right - 1;
		while (i <= j) {
			if (nums[i] < nums[right]) {
				i++;
			} else {
				swap(nums, i, j--);
			}
		}
		swap(nums, i, right); // pivot is at i
		return i;
	}
	private void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}
}
