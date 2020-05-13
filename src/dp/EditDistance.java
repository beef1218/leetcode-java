package dp;
/*
Given two strings of alphanumeric characters, determine the minimum number of Replace, Delete, and Insert operations needed to transform one string into the other.

Assumptions
Both strings are not null
Examples
string one: “sigh”, string two : “asith”
the edit distance between one and two is 2 (one insert “a” at front then replace “g” with “t”).
 */

/*
dp[i][j]: distance from one 0 to i-1 to two 0 to j-1
base case:
dp[0][j]: j
dp[i][j]: i
dp[i][j]: if charAt(i) == charAt(j), dp[i-1][j-1]
          else: min(dp[i-1][j-1], dp[i][j-1], dp[i-1][j]) + 1

return dp[end][end]
*/
public class EditDistance {
	public int editDistance(String one, String two) {
		int[][] dp = new int[2][two.length() + 1];
		for (int i = 0; i <= one.length(); i++) {
			for (int j = 0; j <= two.length(); j++) {
				if (i == 0)
					dp[0][j] = j;
				else if (j == 0)
					dp[i % 2][0] = i;
				else if (one.charAt(i - 1) == two.charAt(j - 1))
					dp[i % 2][j] = dp[(i - 1) % 2][j - 1];
				else
					dp[i % 2][j] = Math.min(Math.min(dp[(i - 1) % 2][j], dp[i % 2][j - 1]), dp[(i - 1) % 2][j - 1]) + 1;
			}
		}
		return dp[one.length() % 2][two.length()];
	}
}