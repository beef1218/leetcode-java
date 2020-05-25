package dp;
/*
There is a wooden stick with length L >= 1, we need to cut it into pieces, where the cutting positions are defined in an int array A.
The positions are guaranteed to be in ascending order in the range of [1, L - 1]. The cost of each cut is the length of the stick segment being cut.
Determine the minimum total cost to cut the stick into the defined pieces.

Examples
L = 10, A = {2, 4, 7}, the minimum total cost is 10 + 4 + 6 = 20 (cut at 4 first then cut at 2 and cut at 7)
 */
/*
cuts: [0, 2, 4, 7, end-1]
dp[i][j]: min cost to cut from i to j
size = 1: dp[i][j] = 0
size = 2: dp[i][j] = cuts[j] - cuts[i] (only 1 cut)
size = 3: dp[i][j] = cuts[j] - cuts[i] + dp[i+1][j] + dp[i][j-1]
dp[i][j]: cuts[j] - cuts[i] + min of dp[i][k] + dp[k][j] (k from i + 1 to j - 1)

    i,k    i,j
          k,j
fill the grid from bottom to top, left to right
return dp[0][cuts.length - 1]

Time: n ^ 3
Space: n ^ 2
*/
public class CuttingWood1 {
	public int minCost(int[] cuts, int length) {
		if (cuts == null || cuts.length == 0 || length <= 0)
			return 0;

		int[] array = new int[cuts.length + 2];
		for (int i = 0; i < cuts.length; i++)
			array[i + 1] = cuts[i];

		array[array.length - 1] = length;
		int[][] dp = new int[cuts.length + 2][cuts.length + 2];
		for (int i = array.length - 1; i >= 0; i--) {
			for (int j = i + 2; j < array.length; j++) {
				int min = Integer.MAX_VALUE;
				for (int k = i + 1; k <= j - 1; k++) {
					min = Math.min(min, dp[i][k] + dp[k][j]);
				}
				dp[i][j] = min + array[j] - array[i];
			}
		}
		return dp[0][array.length - 1];
	}
}
