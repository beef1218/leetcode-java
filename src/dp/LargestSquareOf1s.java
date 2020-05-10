package dp;
/*
Determine the largest square of 1s in a binary matrix (a binary matrix only contains 0 and 1), return the length of the largest square.

dp[i][j]: largest square of 1 that ends at i,j
dp[0][j]: dp[0][j] == 1 ? 1 : 0
dp[i][0]: dp[i][0] == 1 ? 1 : 0
dp[i][j]: if matrix[i][j] == 0: 0
          else: min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]) + 1
keep updating global max. return global max
Time: O(N ^ 2)
Space: O(N)
 */

public class LargestSquareOf1s {
	public int largest(int[][] matrix) {
		int max = 0;
		int size = matrix.length;
		int[][] dp = new int[2][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (matrix[i][j] == 0) {
					dp[i % 2][j] = 0;
					continue;
				}
				if (i == 0 || j == 0)
					dp[i % 2][j] = 1;
				else
					dp[i % 2][j] = Math.min(Math.min(dp[(i - 1) % 2][j], dp[i % 2][j - 1]), dp[(i - 1) % 2][j - 1]) + 1;

				max = Math.max(max, dp[i % 2][j]);
			}
		}
		return max;
	}
}
