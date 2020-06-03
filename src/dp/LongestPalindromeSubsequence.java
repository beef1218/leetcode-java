package dp;
/*
Given a string s, return the longest palindrome subsequence.
Example: s = "abca"
Output: 3
Explanation: The longest palindrome subsequences could be "aba" or "aca".
 */
/*
dp[i][j]: longest palindrome from [i,j]
base case:
i == j: 1
i = j - 1: char at i == char at j ? 2 : 1
dp[i][j]: if char at i == char at j: max(dp[i+1][j], dp[i][j-1], dp[i+1][j-1] + 2)
          else: max(dp[i+1][j], dp[i][j-1])
return dp[0][j]
bottom to top, left to right

  i,j-1    i,j
  i+1,j-1 i+1,j

time: n ^ 2
space: n
*/
public class LongestPalindromeSubsequence {
	public int longestPalindrome(String input) {
		if (input == null || input.length() == 0)
			return 0;

		int size = input.length();
		int[][] dp = new int[2][size];
		for (int i = size - 1; i >= 0; i--) {
			for (int j = i; j < size; j++) {
				if (i == j)
					dp[i%2][j] = 1;
				else if (i == j - 1)
					dp[i%2][j] = input.charAt(i) == input.charAt(j) ? 2 : 1;
				else {
					if (input.charAt(i) == input.charAt(j)) {
						dp[i%2][j] = Math.max(Math.max(dp[(i + 1)%2][j], dp[i%2][j - 1]), dp[(i + 1)%2][j - 1] + 2);
					} else {
						dp[i%2][j] = Math.max(dp[(i + 1)%2][j], dp[i%2][j - 1]);
					}
				}
			}
		}
		return dp[0][size - 1];
	}
}
