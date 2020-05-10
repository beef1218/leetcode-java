package dp;

/*
Given three strings A, B and C. Determine if C can be created by merging A and B in a way that maintains the relative order of the characters in A and B.
Examples
C = "abcde", A = "acd", B = "be", return true
C = "abcde", A = "adc", B = "be", return false
 */
public class InterleaveStrings {
	/*
	dp[i][j]: used a from 0 to i-1 and b from 0 to j - 1
	pointer c: i + j - 1
	dp[0][0]: true
	dp[0][j]: dp[0][j-1] is true && b at j-1 == c at j-1
	dp[i][0]: dp[i-1][0] is true && a at i-1 == c at i-1
	dp[i][j]: case 1: dp[i-1][j] is true && a at i-1 == c at i + j - 1
			  case 2: dp[i][j-1] is true && b at j-1 == c at i + j - 1

	return dp[a.length()][b.length()]
	Time: O(a.length() * b.length())
	Space: can optimize to O(a length || b length)
	*/
	public boolean canMergeDP(String a, String b, String c) {
		if (a == null || b == null || c == null || a.length() + b.length() != c.length())
			return false;

		boolean[][] dp = new boolean[a.length() + 1][b.length() + 1];
		for (int i = 0; i <= a.length(); i++) {
			for (int j = 0; j <= b.length(); j++) {
				if (i == 0 && j == 0)
					dp[0][0] = true;
				else if (i == 0)
					dp[0][j] = dp[0][j - 1] && b.charAt(j - 1) == c.charAt(j - 1);
				else if (j == 0)
					dp[i][0] = dp[i - 1][0] && a.charAt(i - 1) == c.charAt(i - 1);
				else
					dp[i][j] = dp[i - 1][j] && a.charAt(i - 1) == c.charAt(i + j - 1) || dp[i][j - 1] && b.charAt(j - 1) == c.charAt(i + j - 1);
			}
		}
		return dp[a.length()][b.length()];
	}

	/*
	dfs
	number of levels: length of c
	at each level, pick from a or b

	Time: 2 ^ length of C
	Space: length of C
	*/

	public boolean canMergeDFS(String a, String b, String c) {
		if (a == null || b == null || c == null || a.length() + b.length() != c.length())
			return false;

		return dfs(0, 0, 0, a, b, c);
	}

	private boolean dfs(int i, int j, int k, String a, String b, String c) {
		if (k == c.length())
			return true;

		if (i < a.length() && c.charAt(k) == a.charAt(i)) {
			if (dfs(i + 1, j, k + 1, a, b, c))
				return true;
		}
		if (j < b.length() && c.charAt(k) == b.charAt(j)) {
			if (dfs(i, j + 1, k + 1, a, b, c))
				return true;
		}
		return false;
	}
}
