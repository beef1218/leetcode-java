package dp;
/*
There are a row of n houses, each house can be painted with one of the three colors: red, blue or green.
The cost of painting each house with a certain color is different.
You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x 3 cost matrix.
For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on...
Find the minimum cost to paint all houses.
 */
/*
dp[i][j]: paint from 0 to ith houses with ith house using paint j
dp[0][j]: get from costs[0]
dp[i][j]: dp[i][0]: min(dp[i-1][1], dp[i-1][2]) + costs[i][0]
          dp[i][1]: min(dp[i-1][2], dp[i-1][0]) + costs[i][1]
          dp[i][2]: min(dp[i-1][0], dp[i-1][1]) + costs[i][2]
return min(dp[size][0], dp[size][1], dp[size][2])
Time: O(n)
Space: O(1)
*/
public class PaintHouse1 {
	public int minCost(int[][] costs) {
		if (costs == null || costs.length == 0)
			return 0;

		int size = costs.length;
		int[][] dp = new int[2][3];
		dp[0] = costs[0];
		for (int i = 1; i < size; i++) {
			dp[i % 2][0] = Math.min(dp[(i - 1) % 2][1], dp[(i - 1) % 2][2]) + costs[i][0];
			dp[i % 2][1] = Math.min(dp[(i - 1) % 2][2], dp[(i - 1) % 2][0]) + costs[i][1];
			dp[i % 2][2] = Math.min(dp[(i - 1) % 2][0], dp[(i - 1) % 2][1]) + costs[i][2];
		}
		return Math.min(Math.min(dp[(size - 1) % 2][0], dp[(size - 1) % 2][1]), dp[(size - 1) % 2][2]);
	}
}
