package array;

import java.util.Arrays;

public class MaxLengthOfPairChain {
	/*
	method1: dp
	1. sort them by first element
	2. dp[i]: max length chain that ends at i
	dp[0] = 1
	dp[i]: for j from [0, i - 1]
		  if (pairs[i][0] > pairs[j][1])
			  max(dp[j] + 1), update global max
	return global max
	Time: (n ^ 2)
	Space: n
	*/
	public int findLongestChain1(int[][] pairs) {
		if (pairs == null || pairs.length == 0)
			return 0;

		Arrays.sort(pairs, (a, b) -> a[0] - b[0]);
		int max = 1;
		int[] dp = new int[pairs.length];
		dp[0] = 1;
		for (int i = 1; i < dp.length; i++) {
			dp[i] = 1;
			for (int j = 0; j <= i - 1; j++) {
				if (pairs[i][0] > pairs[j][1])
					dp[i] = Math.max(dp[i], dp[j] + 1);
			}
			max = Math.max(max, dp[i]);
		}
		return max;
	}

	/*
	method2: greedy
	1. sort by the second element of the pairs
	2. start by adding the pair, keep updating the ending with the second element of the pair
	3. return count
	Time: O(nlogn)
	Space: O(1)
	*/
	public int findLongestChain2(int[][] pairs) {
		if (pairs == null || pairs.length == 0)
			return 0;

		Arrays.sort(pairs, (a, b) -> a[1] - b[1]);
		int end = Integer.MIN_VALUE;
		int count = 0;
		for (int[] pair : pairs) {
			if (pair[0] > end) {
				end = pair[1];
				count++;
			}
		}
		return count;
	}
}
