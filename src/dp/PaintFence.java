package dp;

/*
There is a fence with n posts, each post can be painted with one of the k colors.
You have to paint all the posts such that no more than two adjacent fence posts have the same color.
Return the total number of ways you can paint the fence.

dp[i]: number of ways to paint [0, i] posts
dp[0]: k
dp[1]: k * k
dp[i]: sum of case1: if i color is different from i-1: dp[i-1] * (k-1)
              case2: if i color is the same as i-1: dp[i-2] * (k - 1)
return dp[n-1]

Time: O(n)
Space: O(1);
 */
public class PaintFence {
	public int numWays(int n, int k) {
		if (n <= 0 || k <= 0)
			return 0;

		int[] dp = new int[3];
		dp[0] = k;
		dp[1] = k * k;
		for (int i = 2; i < n; i++) {
			dp[i % 3] = 0;
			dp[i % 3] += dp[(i - 1) % 3] * (k - 1);
			dp[i % 3] += dp[(i - 2) % 3] * (k - 1);
		}
		return dp[(n - 1) % 3];
	}
}
