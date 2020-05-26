package dp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
You are given coins of different denominations and a total amount of money amount.
Write a function to compute the fewest number of coins that you need to make up that amount.
If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:
Input: coins = [1, 2, 5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1
 */
/*
dp[i]: min number of coins to change i amount
prefill dp[] to -1
dp[i]: case1: i is a coin: 1
       case2: for each coin: if coin < i && dp[i-coin] is not -1,
                            dp[i] = dp[i] == -1? dp[i-coin] + 1 : min(dp[i], dp[i-coin] + 1)
       case3: no solution: -1
Time: O(n) (assume coins length << amount)
Space: O(n)
*/
public class CoinChange {
	public int coinChange(int[] coins, int amount) {
		if (coins == null || coins.length == 0 || amount <= 0)
			return 0;

		Set<Integer> set = new HashSet<>();
		for (int coin : coins)
			set.add(coin);

		int[] dp = new int[amount + 1];
		Arrays.fill(dp, -1);
		for (int i = 1; i <= amount; i++) {
			if (set.contains(i)) {
				dp[i] = 1;
				continue;
			}
			for (int coin : coins) {
				if (i - coin > 0 && dp[i - coin] != -1)
					dp[i] = dp[i] == -1 ? dp[i - coin] + 1 : Math.min(dp[i], dp[i - coin] + 1);
			}
		}
		return dp[amount];
	}
}
