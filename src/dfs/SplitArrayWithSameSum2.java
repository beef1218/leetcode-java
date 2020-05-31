package dfs;

import java.util.ArrayList;
import java.util.List;

/*
Given an input array with integers, how to split the array into k subarrays such that each subarray shares the same sum.
The subarrays cannot be adjacent to each other


    3, -1,| (1, ,1 ,1, 2, 2)| 4, 6, -8,| 1, 1, k=3
0   3   2    3   4  5  7  9  13 19  11  12  13

{3, -1}, {4, 6, -8}, {1, 1}
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
public class SplitArrayWithSameSum2 {
	public static void main(String[] args) {
		SplitArrayWithSameSum2 solution = new SplitArrayWithSameSum2();
		// test case 1
		int[] input = new int[]{3, -1, 4, 6, -8, 1, 1};
		int k = 3;
		List<List<List<Integer>>> result = solution.splitArray(input, k);
		System.out.println(result);

		// test case 2
//		int[] input2 = new int[]{0, 0, 0, 0, 0};
//		int k2 = 3;
//		List<List<List<Integer>>> result2 = solution.splitArray(input2, k2);
//		System.out.println(result2);
	}
	public List<List<List<Integer>>> splitArray(int[] input, int k) {
		List<List<List<Integer>>> result = new ArrayList<>();
		if (input == null || input.length < k || k <= 1) {
			return result;
		}
		int[] prefixSum = new int[input.length + 1];
		for (int i = 1; i < prefixSum.length; i++) {
			prefixSum[i] = prefixSum[i - 1] + input[i - 1];
		}
		dfs(0, 0, Integer.MIN_VALUE, input, prefixSum, k, new ArrayList<List<Integer>>(), result);
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
	private void dfs(int chunkIndex, int index, int sum, int[] input, int[] prefixSum, int k, List<List<Integer>> cur, List<List<List<Integer>>> result) {
		if (chunkIndex == k) {
			List<List<Integer>> copy = new ArrayList<>();
			for (List<Integer> list : cur) {
				copy.add(new ArrayList<>(list));
			}
			result.add(copy);
			return;
		}
		boolean needRemove = false;
		for (int i = index; i < prefixSum.length - k + chunkIndex; i++) {
			for (int j = i + 1; j <= prefixSum.length - k + chunkIndex; j++) {
				int curSum = prefixSum[j] - prefixSum[i];
				if (sum != Integer.MIN_VALUE && curSum != sum) {
					continue;
				}
				List<Integer> chunk = new ArrayList<>();
				cur.add(chunk);
				needRemove = true;
				for (int m = i + 1; m <= j; m++) {
					chunk.add(input[m - 1]);
				}
				dfs(chunkIndex + 1, j, curSum, input, prefixSum, k, cur, result);
			}
		}
		if (needRemove) {
			cur.remove(cur.size() - 1);
		}
	}
}
