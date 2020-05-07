package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
https://leetcode.com/problems/jump-game-v/
Given an array of integers arr and an integer d. In one step you can jump from index i to index:

i + x where: i + x < arr.length and 0 < x <= d.
i - x where: i - x >= 0 and 0 < x <= d.
In addition, you can only jump from index i to index j if arr[i] > arr[j] and arr[i] > arr[k] for all indices k between i and j (More formally min(i, j) < k < max(i, j)).

You can choose any index of the array and start jumping. Return the maximum number of indices you can visit.

Notice that you can not jump outside of the array at any time.

dfs + memoization
time: O(n)
space: O(n)
 */
public class JumpGame5 {
	public int maxJumps(int[] arr, int d) {
		if (arr == null || arr.length == 0 || d <= 0)
			return 0;

		int[] memo = new int[arr.length];
		Arrays.fill(memo, -1);
		int max = 0;
		for (int i = 0; i < arr.length; i++) {
			max = Math.max(max, dfs(i, d, arr, memo));
		}
		return max;
	}

	private int dfs(int i, int d, int[] arr, int[] memo) {
		if (memo[i] != -1)
			return memo[i];

		List<Integer> nexts = getNexts(i, d, arr);
		if (nexts.size() == 0) {
			memo[i] = 1;
			return 1;
		}
		for (int next : nexts) {
			memo[i] = Math.max(memo[i], dfs(next, d, arr, memo) + 1);
		}
		return memo[i];
	}

	private List<Integer> getNexts(int i, int d, int[] arr) {
		List<Integer> nexts = new ArrayList<>();
		for (int left = i - 1; left >= 0 && left >= i - d; left--) {
			if (arr[left] >= arr[i])
				break;

			nexts.add(left);
		}
		for (int right = i + 1; right < arr.length && right <= i + d; right++) {
			if (arr[right] >= arr[i])
				break;

			nexts.add(right);
		}
		return nexts;
	}
}
