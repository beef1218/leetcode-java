package dfs;

import java.util.ArrayList;
import java.util.List;

/*
Given an input array with integers, how to split the array into k subarrays such that each subarray shares the same sum.
The subarrays cannot be adjacent to each other.
If there are multiple valid results, only return one of them.


    3, -1,| (1, ,1 ,1, 2, 2)| 4, 6, -8,| 1, 1, k=3
0   3   2    3   4  5  7  9  13 19  11  12  13

{3, -1}, {4, 6, -8}, {1, 1}
 */
/*
dfs strategy

recursion tree:
number of levels: k
each level: use nested for loop to get all possible subarrays for ith chunk

						root
			/  |   |   |   |   \
		[0,0] [0,1] [0,2] ... [0,n-k], [1,1],[1,2]...[1,n-k] ... [n-k,n-k]        - n ^ 2
			/  |  |  |  \
		[2,2],[2,3]..[n-k+1,n-k+1]			                                      - (n-1) ^ 2


base case: level == k || end of array is reached
at each level: use a loop to keep adding elements, when current sum == target sum, do recursion
Note: at first chunk, we do not have a sum, so we use MIN_VALUE as a special value to indicate the first chunk

Return:
list of list, each inner list has two numbers to represent the start and end indexes of each subarray

Time: k * n ^ 2
Space: n

 */
public class SplitArrayWithSameSum2 {
	public static void main(String[] args) {
		SplitArrayWithSameSum2 solution = new SplitArrayWithSameSum2();
		// test case 1
		int[] input = new int[]{3, -1, 1, 1, 1, 4, 6, -8, 1, 1};
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
		int[] prefixSum = new int[input.length + 1];
		for (int i = 1; i < prefixSum.length; i++) {
			prefixSum[i] = prefixSum[i - 1] + input[i - 1];
		}
		dfs(0, 0, Integer.MIN_VALUE, prefixSum, k, result);
		return result;
	}

	private boolean dfs(int chunkIndex, int arrayIndex, int targetSum, int[] prefixSum, int k, List<List<Integer>> result) {
		if (chunkIndex == k) {
			return true;
		}
		for (int i = arrayIndex; i < prefixSum.length - k + chunkIndex; i++) {
			for (int j = i + 1; j <= prefixSum.length - k + chunkIndex; j++) {
				int curSum = prefixSum[j] - prefixSum[i];
				if (targetSum != Integer.MIN_VALUE && curSum != targetSum) {
					continue;
				}
				List<Integer> chunk = new ArrayList<>();
				chunk.add(i);
				chunk.add(j - 1);
				result.add(chunk);
				if (dfs(chunkIndex + 1, j, curSum, prefixSum, k, result)) {
					return true;
				}
				result.remove(result.size() - 1);
			}
		}
		return false;
	}
}
