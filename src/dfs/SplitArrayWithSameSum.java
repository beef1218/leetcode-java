package dfs;

import java.util.ArrayList;
import java.util.List;

/*
Given an input array with integers, how to split the array into k subarrays such that each subarray shares the same sum
 */
/*
dfs strategy

recursion tree:
number of levels: k
each node has up to n - k + level number of branches

base case: level == k
at each level: use a loop to keep adding elements, when current sum == target sum, do recursion
Note: at first chunk, we do not have a sum, so we use MIN_VALUE as a special value to indicate the first chunk
 */
public class SplitArrayWithSameSum {
	public static void main(String[] args) {
		SplitArrayWithSameSum solution = new SplitArrayWithSameSum();
		// test case 1
		int[] input = new int[]{3, -1, 4, 6, -8, 1, 1};
		int k = 3;
		List<List<List<Integer>>> result = solution.splitArray(input, k);
		System.out.println(result);

		// test case 2
		int[] input2 = new int[]{0, 0, 0, 0, 0};
		int k2 = 3;
		List<List<List<Integer>>> result2 = solution.splitArray(input2, k2);
		System.out.println(result2);
	}
	public List<List<List<Integer>>> splitArray(int[] input, int k) {
		List<List<List<Integer>>> result = new ArrayList<>();
		if (input == null || input.length < k || k <= 1) {
			return result;
		}
		dfs(0, 0, Integer.MIN_VALUE, input, k, new ArrayList<List<Integer>>(), result);
		return result;
	}
	/**
	 * do dfs and add to result
	 * @param chunkIndex: the index of chunk we are at
	 * @param index: the index of input array we are at
	 * @param sum: the sum of each chunk. If sum == MIN_VALUE, that means we are at the first chunk
	 * @param k: total number of chunk we need
	 * @param input: the input array
	 * @param cur: our current result   
	 * @param result: the final result
	 */
	private void dfs(int chunkIndex, int index, int sum, int[] input, int k, List<List<Integer>> cur, List<List<List<Integer>>> result) {
		// base case
		if (chunkIndex == k) {
			if (index == input.length) {
				// do a deep copy and add cur to result
				List<List<Integer>> copy = new ArrayList<>();
				for (List<Integer> list : cur) {
					copy.add(new ArrayList<>(list));
				}
				result.add(copy);
			}
			return;
		}
		int curSum = 0;
		List<Integer> chunk = new ArrayList<>();
		cur.add(chunk);
		for (int i = index; i <= input.length - k + chunkIndex; i++) {
			curSum += input[i];
			chunk.add(input[i]);
			if (sum == Integer.MIN_VALUE || curSum == sum) { // if sum is MIN_VALUE, we are at the first chunk
				dfs(chunkIndex + 1, i + 1, curSum, input, k, cur, result);
			}
		}
		cur.remove(cur.size() - 1);
	}
}
