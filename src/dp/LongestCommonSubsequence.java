package dp;

/*
dp[i][j]: longest common subsequence between source [0, i-1] and target [0, j-1]
dp[0][j]: 0
dp[i][0]: 0
dp[i][j]: if source at i == target at j: dp[i-1][j-1] + 1
          else: max(dp[i-1][j], dp[i][j-1])

return dp[source.length()][target.length()]
Time: O(m x n)
Space: O(m)
*/
public class LongestCommonSubsequence {
	public int longest(String source, String target) {
		if (source == null || target == null || source.length() == 0 || target.length() == 0)
			return 0;

		int[][] dp = new int[2][target.length() + 1];
		for (int i = 0; i <= source.length(); i++) {
			for (int j = 0; j <= target.length(); j++) {
				if (i == 0 || j == 0)
					dp[i % 2][j] = 0;
				else if (source.charAt(i - 1) == target.charAt(j - 1))
					dp[i % 2][j] = dp[(i - 1) % 2][j - 1] + 1;
				else
					dp[i % 2][j] = Math.max(dp[(i - 1) % 2][j], dp[i % 2][j - 1]);
			}
		}
		return dp[(source.length()) % 2][target.length()];
	}
}
