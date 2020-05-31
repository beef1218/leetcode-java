package array;
/*
There are two sorted arrays nums1 and nums2 of size m and n respectively.
Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
You may assume nums1 and nums2 cannot be both empty.
 */

/*
Get kth smallest number in two sorted arrays using binary search
Use two pointers to keep track of the start of the remaining elements in both arrays

case1: if one array has no element left, return the kth element of the other array
case2: k == 1: return min(nums1[index1], nums2[index2])
case3: if one array doesn't have k/2 elements left, remove k/2 from the other array
case4: compare the k/2th element in both array, remove k/2 elements from the smaller one

Time: if find kth smallest element: O(logk)
	  if find median: O(m + n)
Space: O(1)
*/
public class MedianOfTwoSortedArrays {
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int totalLength = nums1.length + nums2.length;
		if (totalLength % 2 != 0) {
			return (double) helper(nums1, nums2, 0, 0, totalLength / 2 + 1);
		} else {
			int result1 = helper(nums1, nums2, 0, 0, totalLength / 2);
			int result2 = helper(nums1, nums2, 0, 0, totalLength / 2 + 1);
			return (result1 + result2) / 2.0;
		}
	}

	private int helper(int[] nums1, int[] nums2, int index1, int index2, int k) {
		if (index1 >= nums1.length) {
			return nums2[index2 + k - 1];
		}
		if (index2 >= nums2.length) {
			return nums1[index1 + k - 1];
		}
		if (k == 1) {
			return Math.min(nums1[index1], nums2[index2]);
		}
		int num1 = index1 + k / 2 - 1 >= nums1.length ? Integer.MAX_VALUE : nums1[index1 + k / 2 - 1];
		int num2 = index2 + k / 2 - 1 >= nums2.length ? Integer.MAX_VALUE : nums2[index2 + k / 2 - 1];
		if (num1 < num2) {
			return helper(nums1, nums2, index1 + k / 2, index2, k - k / 2);
		} else {
			return helper(nums1, nums2, index1, index2 + k / 2, k - k / 2);
		}
	}
}
