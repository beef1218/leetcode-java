package dfs;

import java.util.ArrayList;
import java.util.List;

/*
Given an input array with integers, how to split the array into k subarrays such that each subarray shares the same sum
 */
/*
dfs strategy
1. get total sum and divide k to get the sum of each subarray
2. dfs
3. return a list of list of endIndex of the split position.
recursion tree:
number of levels: k
each node has up to n - k + level number of branches

base case: level == k || end of array is reached
at each level: use a for loop to get curSum, when curSum == target sum, do recursion
*/
public class SplitArrayWithSameSum {
	public static void main(String[] args) {
		SplitArrayWithSameSum solution = new SplitArrayWithSameSum();
		// test case 1
		int[] input = new int[]{3, -1, 4, 6, -8, 1, 1};
		int k = 3;
		List<List<Integer>> result = solution.splitArray(input, k);
		System.out.println(result);

		// test case 2
		int[] input2 = new int[]{0, 0, 0, 0, 0};
		int k2 = 3;
		List<List<Integer>> result2 = solution.splitArray(input2, k2);
		System.out.println(result2);
	}

	public List<List<Integer>> splitArray(int[] input, int k) {
		List<List<Integer>> result = new ArrayList<>();
		if (input == null || input.length < k || k <= 1) {
			return result;
		}
		int totalSum = 0;
		for (int num : input) {
			totalSum += num;
		}
		dfs(0, 0, totalSum / k, input, k, new ArrayList<>(), result);
		return result;
	}

	private void dfs(int chunkIndex, int arrayIndex, int targetSum, int[] input, int k, List<Integer> cur, List<List<Integer>> result) {
		// base case
		if (chunkIndex == k || arrayIndex == input.length) {
			if (chunkIndex == k && arrayIndex == input.length) {
				result.add(new ArrayList<>(cur));
			}
			return;
		}
		int curSum = 0;
		for (int i = arrayIndex; i <= input.length - k + chunkIndex; i++) {
			curSum += input[i];
			if (curSum == targetSum) {
				cur.add(i);
				dfs(chunkIndex + 1, i + 1, targetSum, input, k, cur, result);
				cur.remove(cur.size() - 1);
			}
		}
	}
}
