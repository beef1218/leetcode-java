package array;

/*
Given a non-negative integer array representing the heights of a list of adjacent bars. Suppose each bar has a width of 1.
Find the largest amount of water that can be trapped in the histogram.

Examples
{ 2, 1, 3, 2, 4 }, the amount of water can be trapped is 1 + 1 = 2 (at index 1, 1 unit of water can be trapped and index 3, 1 unit of water can be trapped)
 */
/*
use two pointers i, j; one at each side. leftMax = array[0]; rightmax = array[array.length - 1]
if leftMax < rightMax:
  sum += leftMax - array[i] > 0 ? leftMax - array[i] : 0;
  update leftMax
  i++;
else
  sum += rightMax - array[j] > 0 ? rightMax - array[j] : 0;
  update rightMax
  j--;

terminate when i move past j
Time: O(n)
Space: O(1)
*/
public class MaxWaterTrapped1 {
	public int maxTrapped(int[] array) {
		if (array == null || array.length <= 2)
			return 0;

		int i = 1;
		int j = array.length - 2;
		int sum = 0;
		int leftMax = array[0];
		int rightMax = array[array.length - 1];
		while (i <= j) {
			if (leftMax < rightMax) {
				sum += leftMax - array[i] > 0 ? leftMax - array[i] : 0;
				leftMax = Math.max(leftMax, array[i]);
				i++;
			} else {
				sum += rightMax - array[j] > 0 ? rightMax - array[j] : 0;
				rightMax = Math.max(rightMax, array[j]);
				j--;
			}
		}
		return sum;
	}
}
