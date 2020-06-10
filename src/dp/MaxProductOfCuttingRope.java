package dp;
/*
Given a rope with positive integer-length n, how to cut the rope into m integer-length parts with length p[0], p[1], ...,p[m-1],
in order to get the maximal product of p[0]*p[1]* ... *p[m-1]? m is determined by you and must be greater than 0 (at least one cut must be made).
Return the max product you can have.

Assumptions
n >= 2
Examples

n = 12, the max product is 3 * 3 * 3 * 3 = 81(cut the rope into 4 pieces with length of each is 3).
 */

/*
dp[i]: max product with length of i
dp[1]: 1 (not really valid)
dp[i]: j from 1 to i-1, max of (max(dp[j], j)*(i-j))
return dp[length]

Time: O(n)
Space: O(n)
*/
public class MaxProductOfCuttingRope {
	/*
	DP bottom up
	 */
	public int maxProduct(int length) {
		int[] dp = new int[length + 1];
		dp[1] = 1;
		for (int i = 2; i <= length; i++) {
			for (int j = 1; j <= i - 1; j++) {
				dp[i] = Math.max(dp[i], Math.max(dp[j], j) * (i - j));
			}
		}
		return dp[length];
	}

	/*
	Top down recursion + memoization
	 */
	public int maxProduct2(int length) {
		int[] max = new int[]{0};
		int[] memo = new int[length + 1];
		return helper(length, memo);
	}
	private int helper(int length, int[] memo) {
		if (length == 1) {
			return 1;
		}
		if (memo[length] > 0) {
			return memo[length];
		}
		for (int i = 1; i < length; i++) {
			memo[length] = Math.max(memo[length], i * Math.max(helper(length - i, memo), length - i));
		}
		return memo[length];
	}
}
