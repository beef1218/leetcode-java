package fb;

/*
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.

Example:
Input: [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
 */
/*
Greedy:
1. Keep track of leftMax and rightMax. Init: leftMax = height[0]; rightMax = height[height.length - 1]
2. Use two pointer. One at each side. Init: left = 1; right = height.length - 2;
3. if leftMax < rightMax: calculate water trapped at left; update leftMax; left++
   else: calculate water trapped at right; update rightMax; right--
Terminate when left > right

Time: O(n)
Space: O(1)
 */
public class TrappingRainWater {
    public int trap(int[] height) {
        if (height == null || height.length <= 2) {
            return 0;
        }
        int total = 0;
        int left = 1;
        int right = height.length - 2;
        int leftMax = height[0];
        int rightMax = height[height.length - 1];
        while (left <= right) {
            if (leftMax < rightMax) {
                total += height[left] < leftMax ? leftMax - height[left] : 0;
                leftMax = Math.max(leftMax, height[left]);
                left++;
            } else {
                total += height[right] < rightMax ? rightMax - height[right] : 0;
                rightMax = Math.max(rightMax, height[right]);
                right--;
            }
        }
        return total;
    }
}
