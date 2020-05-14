package dp;
/*
Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums.
You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins.
Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.
Find the maximum coins you can collect by bursting the balloons wisely.
 */
/*
dp[i][j]: max coins from bursting [i, j] balloons with all other ballons present

size = 1: array[i-1] * array[i] * array[i+1]
size = n: j = i + size - 1; for k from [i, j], dp[i][k-1] + dp[k+1][j] + array[k] * array[j-1] * array[j+1]
return dp[1][nums.length - 2]
*/
public class BurstBalloons {
	public static void main(String[] args) {
		int[] input = new int[]{3, 1, 5, 8};
		BurstBalloons solution = new BurstBalloons();
		int result = solution.maxCoins(input);
		System.out.println(result);
	}
	public int maxCoins(int[] nums) {
		if (nums == null || nums.length == 0)
			return 0;

		int[] array = new int[nums.length + 2];
		for (int i = 0; i < nums.length; i++)
			array[i + 1] = nums[i];

		array[0] = 1;
		array[array.length - 1] = 1;
		int[][] dp = new int[array.length][array.length];
		for (int s = 1; s <= nums.length; s++) {
			for (int i = 1; i + s - 1 < array.length - 1; i++) {
				int j = i + s - 1;
				if (i == j)
					dp[i][j] = array[i - 1] * array[i] * array[i + 1];
				else {
					for (int k = i; k <= j; k++) {
						dp[i][j] = Math.max(dp[i][j], dp[i][k - 1] + dp[k + 1][j] + array[k] * array[i - 1] * array[j + 1]);
					}
				}
			}
		}
		return dp[1][array.length - 2];
	}
}
