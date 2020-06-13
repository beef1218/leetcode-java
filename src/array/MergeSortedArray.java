package array;

/*
Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

Note:
The number of elements initialized in nums1 and nums2 are m and n respectively.
You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
Example:

Input:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3
Output: [1,2,2,3,5,6]
 */
/*
1. use a pointer (end) to point to the end of nums1 (index is m + n - 1)
2. use two pointers (p1 and p2) to point to the end of nums1 and nums2, copy the greater one to end
3. if p1 reaches 0 first, copy the remaining of nums2 to nums1
   else, we are done
Time: O(n)
Space: O(1)
*/
public class MergeSortedArray {
	public void merge(int[] nums1, int m, int[] nums2, int n) {
		int end = m + n - 1;
		int i = m - 1;
		int j = n - 1;
		while (i >= 0 && j >= 0) {
			if (nums1[i] > nums2[j]) {
				nums1[end--] = nums1[i--];
			} else {
				nums1[end--] = nums2[j--];
			}
		}
		while (j >= 0) {
			nums1[end--] = nums2[j--];
		}
	}
}
