package dp;

/*
Given a string with only character 'a' and 'b', replace some of the characters such that the string becomes in the forms of all the 'b's are on the right side of all the 'a's.
Determine what is the minimum number of replacements needed.
 */
/*
dp[i][j]: min steps for input from i to j
if i == j: 0
if i = j - 1: input at i == b && input at j == a ? 1 : 0
dp[i][j]: if input at i == a: dp[i+1][j]
          if input at j == b: dp[i][j-1]
          else (i is b, j is a): min(dp[i+1][j], dp[i][j-1]) + 1

return dp[0][input.length - 1]
bottom to top; left to right
*/
public class ReplacementsOfAandB {
	public int minReplacements(String input) {
		if (input == null || input.length() <= 1)
			return 0;

		int size = input.length();
		int[][] dp = new int[size][size];
		for (int i = size - 1; i >= 0; i--) {
			for (int j = i; j < size; j++) {
				if (i == j)
					dp[i][j] = 0;
				else if (i == j - 1)
					dp[i][j] = input.charAt(i) == 'b' && input.charAt(j) == 'a' ? 1 : 0;
				else {
					if (input.charAt(i) == 'a')
						dp[i][j] = dp[i + 1][j];
					else if (input.charAt(j) == 'b')
						dp[i][j] = dp[i][j - 1];
					else
						dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
				}
			}
		}
		return dp[0][size - 1];
	}
}
