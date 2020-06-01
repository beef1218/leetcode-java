package amazon;

/*
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!

Example:
Input: [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
 */
/*
greedy:
1. two pointers, left=1 and right=length-2
2. keep track of leftMax and rightMax
3. if leftMax <= rightMax, calculate water at left; move left; update leftMax
4. terminate when left > right
Time: O(n)
Space: O(1)
*/
public class TrappingRainWater {
	public int trap(int[] height) {
		if (height == null || height.length <= 2) {
			return 0;
		}
		int total = 0;
		int leftMax = height[0];
		int rightMax = height[height.length - 1];
		int left = 1;
		int right = height.length - 2;
		while (left <= right) {
			if (leftMax <= rightMax) {
				total += Math.max(0, leftMax - height[left]);
				leftMax = Math.max(leftMax, height[left]);
				left++;
			} else {
				total += Math.max(0, rightMax - height[right]);
				rightMax = Math.max(rightMax, height[right]);
				right--;
			}
		}
		return total;
	}
}
