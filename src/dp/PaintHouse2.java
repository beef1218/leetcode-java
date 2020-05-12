package dp;
/*
There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different.
You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x k cost matrix.
For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on...
Find the minimum cost to paint all houses.
 */
/*
dp[i][j]: paint from [0, i] houses with ith house using color j
dp[0][j] = costs[0]
dp[i][j]: min of dp[i-1][k] + costs[i][j] where k != j.
return min of last row

       i-1,k
        i,j

top to bottom, left to right
at each row, do a prefix min array
*/
public class PaintHouse2 {
	public static void main(String[] args) {
		PaintHouse2 solution = new PaintHouse2();
		int[][] input = new int[][]{{8,16,12,18,9},{19,18,8,2,8},{8,5,5,13,4},{15,9,3,19,2},{8,7,1,8,17},{8,2,8,15,5},{8,17,1,15,3},{8,8,5,5,16},{2,2,18,2,9}};
		int result = solution.minCostII(input);
		System.out.println(result);
	}

	public int minCostII(int[][] costs) {
		if (costs == null || costs.length == 0)
			return 0;

		int[][] dp = new int[costs.length][costs[0].length];
		for (int j = 0; j < costs[0].length; j++)
			dp[0][j] = costs[0][j];

		for (int i = 1; i < costs.length; i++) {
			int[] mins = getMins(dp[i - 1]);
			for (int j = 0; j < costs[0].length; j++) {
				dp[i][j] = mins[0] == j ? dp[i - 1][mins[1]] + costs[i][j] : dp[i - 1][mins[0]] + costs[i][j];
			}
		}
		int minColorOnLastHouse = getMins(dp[costs.length - 1])[0];
		return dp[costs.length - 1][minColorOnLastHouse];
	}
	/*
	Get the min and second min indexes from previous row
	returns int[]
	int[0]: the color with min cost
	int[1]: the color with second min cost
	 */
	private int[] getMins(int[] array) {
		int min1 = 0;
		int min2 = -1;
		for (int i = 1; i < array.length; i++) {
			if (array[i] < array[min1]) {
				min2 = min1;
				min1 = i;
			} else if (min2 == -1 || array[i] < array[min2]) {
				min2 = i;
			}
		}
		return new int[]{min1, min2};
	}
}
