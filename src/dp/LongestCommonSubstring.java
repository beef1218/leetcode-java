package dp;
/*
dp[i][j]: longest common substring between source [0,i] including i and target [0,j] including j
dp[0][j]: source at 0 == target at j ? 1 : 0
dp[i][0]: source at i == target at 0 ? 1 : 0
dp[i][j]: if source at i != target at j: 0
          else: dp[i-1][j-1] + 1

keep updating global max and end index. When dp[i][j] > max, update max and end.
Time: O(M x N)
Space: O(N) or O(M) which ever is shorter
*/
public class LongestCommonSubstring {
	public String longestCommon(String source, String target) {
		if (source == null || target == null || source.length() == 0 || target.length() == 0)
			return "";

		int maxLength = 0;
		int start = 0;
		int end = 0;
		int[][] dp = new int[2][target.length()];
		for (int i = 0; i < source.length(); i++) {
			for (int j = 0; j < target.length(); j++) {
				if (source.charAt(i) != target.charAt(j)) {
					dp[i%2][j] = 0;
					continue;
				}
				if (i == 0 || j == 0)
					dp[i%2][j] = 1;
				else
					dp[i%2][j] = dp[(i - 1)%2][j - 1] + 1;

				if (dp[i%2][j] > maxLength) {
					maxLength = dp[i%2][j];
					end = i;
					start = end - maxLength + 1;
				}
			}
		}
		return source.substring(start, end + 1);
	}
}
