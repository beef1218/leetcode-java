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
dp[i][j]: distance from i chars from the beginning of one to j chars from the beginning of two
base case:
dp[0][j]: j
dp[i][j]: i
dp[i][j]:
	case1(replace): dp[i-1][j-1] + (charAt(i - 1) == charAt(j - 1) ? 0 : 1)
	case2(add): dp[i][j - 1] + 1
	case3(remove): dp[i - 1][j] + 1
	min(case1, case2, case3)

return dp[end][end]

Time: O(M x N)
Space: O(M x N) -> optimize to min(M, N)
*/
public class EditDistance {
	/*
	Method1: DP
	 */
	public int editDistance(String one, String two) {
		int[][] dp = new int[2][two.length() + 1];
		for (int i = 0; i <= one.length(); i++) {
			for (int j = 0; j <= two.length(); j++) {
				if (i == 0) {
					dp[0][j] = j;
				} else if (j == 0) {
					dp[i % 2][0] = i;
				} else {
					int replace = dp[(i - 1) % 2][j - 1] + (one.charAt(i - 1) == two.charAt(j - 1) ? 0 : 1);
					int remove = dp[(i - 1) % 2][j] + 1;
					int add = dp[i % 2][j - 1] + 1;
					dp[i % 2][j] = Math.min(Math.min(add, remove), replace);
				}
			}
		}
		return dp[one.length() % 2][two.length()];
	}

	/*
	Method2: Top down recursion + memoization
	 */
	public int editDistance2(String one, String two) {
		int[][] memo = new int[one.length()][two.length()];
		return helper(one, two, 0, 0, memo);
	}

	// returns distance from input [i, end] to [j, end]
	private int helper(String one, String two, int i, int j, int[][] memo) {
		if (i == one.length()) {
			return two.length() - j;
		}
		if (j == two.length()) {
			return one.length() - i;
		}
		if (memo[i][j] > 0) {
			return memo[i][j];
		}
		if (one.charAt(i) == two.charAt(j)) {
			return helper(one, two, i + 1, j + 1, memo);
		} else {
			int case1 = helper(one, two, i + 1, j + 1, memo);
			int case2 = helper(one, two, i, j + 1, memo);
			int case3 = helper(one, two, i + 1, j, memo);
			memo[i][j] = Math.min(Math.min(case1, case2), case3) + 1;
			return memo[i][j];
		}
	}
}
