package dp;

/*
dp[i][j]: longest palindromic substring from [i, j] in input including i and j (here we are looking for substreing, not subsequence, so it must be inclusive)
when i == j: dp[i][j] = 1
when i == j - 1: dp[i][j] = input at i == input at j ? 2 : 0
dp[i][j]: case1: input at i == input at j && dp[i+1][j-1] != 0: dp[i+1][j-1] + 2
          case2: input at i != input at j: 0
when dp[i][j] > global max, update left and right pointers

          i-1,j
           i,j    i,j+1
  i+1,j-1

bottom to top, left to right
Time: O(n)
Space: O(n)
*/
public class LongestPalindromicSubstring {
	public String longestPalindrome(String input) {
		if (input == null || input.length() == 0)
			return "";

		int size = input.length();
		int[][] dp = new int[2][size];
		int left = 0;
		int right = 0;
		int max = 1;
		for (int i = size - 1; i >= 0; i--) {
			for (int j = i; j < size; j++) {
				if (i == j)
					dp[i % 2][j] = 1;
				else if (i == j - 1)
					dp[i % 2][j] = input.charAt(i) == input.charAt(j) ? 2 : 0;
				else if (input.charAt(i) == input.charAt(j) && dp[(i + 1) % 2][j - 1] > 0)
					dp[i % 2][j] = dp[(i + 1) % 2][j - 1] + 2;
				else
					dp[i % 2][j] = 0;

				if (dp[i % 2][j] > max) {
					max = dp[i % 2][j];
					left = i;
					right = j;
				}
			}
		}
		return input.substring(left, right + 1);
	}
}
