package amazon;

/*
dp[i][j]: whether input from i to j is palindrome (here we are looking for substring, not subsequence, so it must be include the border)
when i == j: dp[i][j] = true
when i == j - 1: dp[i][j] = input at i == input at j
dp[i][j]: input at i == input at j && dp[i+1][j-1]

when dp[i][j] is true, check j - i + 1 against max length, update left and right pointers
return input.substring(left, right + 1)

          i-1,j
           i,j    i,j+1
  i+1,j-1

bottom to top, left to right
Time: O(n)
Space: O(n)
*/
public class LongestPalindromicSubstring {
	public String longestPalindrome(String s) {
		if (s == null || s.length() == 0)
			return "";

		boolean[][] dp = new boolean[2][s.length()];
		int max = 0;
		int left = -1;
		int right = -1;
		for (int i = s.length() - 1; i >= 0; i--) {
			for (int j = i; j < s.length(); j++) {
				if (s.charAt(i) != s.charAt(j)) {
					dp[i%2][j] = false;
					continue;
				}
				if (i == j || i == j - 1)
					dp[i%2][j] = true;
				else
					dp[i%2][j] = dp[(i + 1)%2][j - 1];

				if (dp[i%2][j] && j - i + 1 > max) {
					max = j - i + 1;
					left = i;
					right = j;
				}
			}
		}
		return s.substring(left, right + 1);
	}
}
