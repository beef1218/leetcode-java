package array;
/*
Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai).
n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
Find two lines, which together with x-axis forms a container, such that the container contains the most water.

Note: You may not slant the container and n is at least 2.

Example:

Input: [1,8,6,2,5,4,8,3,7]
Output: 49
*/
/*
1. use two pointers, one at each side
2. calculate area, update global max, move the shorter pointer towards the other
3. terminate when the two pointers meet
Time: O(n)
Space: O(1)
*/
public class ContainerWithMostWater {
	public int maxArea(int[] height) {
		if (height == null || height.length == 0)
			return 0;

		int i = 0;
		int j = height.length - 1;
		int result = Math.min(height[i], height[j]) * (j - i);
		while (i < j) {
			result = Math.max(result, Math.min(height[i], height[j]) * (j - i));
			if (height[i] < height[j])
				i++;
			else
				j--;
		}
		return result;
	}
}
