package dp;

public class CanIWin1 {

	public static void main(String[] args) {
		CanIWin1 solution = new CanIWin1();
		int[] input = new int[] { 62, 92, 47, 65, 26, 47, 86 };
		boolean result = solution.canWin(input);
		System.out.println(result);

	}

	public boolean canWin(int[] nums) {
		if (nums == null || nums.length <= 1)
			return true;

		int[] preSum = getPreSum(nums);
		int[][] dp = new int[nums.length][nums.length];
		for (int i = nums.length - 1; i >= 0; i--) {
			for (int j = i; j < nums.length; j++) {
				if (i == j)
					dp[i][j] = i;
				else {
					int left = nums[i] + sum(preSum, i + 1, j) - dp[i + 1][j];
					int right = nums[j] + sum(preSum, i, j - 1) - dp[i][j - 1];
					dp[i][j] = Math.max(left, right);
				}
			}
		}
		return dp[0][nums.length - 1] >= preSum[preSum.length - 1] - dp[0][nums.length - 1];
	}

	private int sum(int[] preSum, int i, int j) {
		return i == 0 ? preSum[j] : preSum[j] - preSum[i - 1];
	}

	private int[] getPreSum(int[] nums) {
		int[] preSum = new int[nums.length];
		preSum[0] = nums[0];
		for (int i = 1; i < preSum.length; i++) {
			preSum[i] = preSum[i - 1] + nums[i];
		}
		return preSum;
	}

}
