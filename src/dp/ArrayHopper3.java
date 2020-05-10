package dp;

import java.util.Arrays;

/*
Given an array of non-negative integers, you are initially positioned at index 0 of the array.
A[i] means the maximum jump distance from that position (you can only jump towards the end of the array).
Determine the minimum number of jumps you need to jump out of the array.
By jump out, it means you can not stay at the end of the array. Return -1 if you can not do so.
 */
/*
dp[i]: number of jumps to jump out of the array
dp[length - 1]: array[length - 1] > 0 ? 1 : -1
dp[i]: if (array[i] >= length - i): 1
       else: j from i + 1 to i + array[i], get min of dp[j] that is not -1. dp[i] = dp[j] + 1
return dp[0]

Time: O(n * array values)
Space: O(n)
*/
public class ArrayHopper3 {
	public int minJump(int[] array) {
		if (array == null || array.length == 0)
			return -1;

		int length = array.length;
		int[] dp = new int[length];
		Arrays.fill(dp, -1);
		dp[length - 1] = array[length - 1] > 0 ? 1 : -1;
		for (int i = length - 2; i >= 0; i--) {
			if (array[i] >= length - i)
				dp[i] = 1;
			else {
				for (int j = i + 1; j <= i + array[i]; j++) {
					if (dp[j] > 0)
						dp[i] = dp[i] == -1 ? dp[j] + 1 : Math.min(dp[i], dp[j] + 1);
				}
			}
		}
		return dp[0];
	}
}
