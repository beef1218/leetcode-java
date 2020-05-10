package dp;

import java.util.ArrayDeque;
import java.util.Deque;
/*
1. get prefixSum top
2. for each row, do max rectangle area in histogram

to get max rectangle area in histogram:
1. use a stack to store index
2. scan histogram from left to right:
  2a. if new element < stack top: pop; area = top.value * (new element index - next top value - 1)
  2b. after all element is scanned, push zero (to calculate all remaining areas)

Time: O(M x N)
Space: O(M x N)
*/
public class LargestRectangleOf1s {
	public static void main(String[] args) {
		LargestRectangleOf1s solution = new LargestRectangleOf1s();
		int[][] input1 = new int[][]{{1,1,1,1},{0,0,1,1},{1,0,1,1},{1,1,1,1}};
		int result = solution.largest(input1);
		System.out.println(result);
	}
	public int largest(int[][] matrix) {
		int row = matrix.length;
		int col = matrix[0].length;
		int[][] top = getPreSumTop(matrix, row, col);
		int max = 0;
		for (int[] array : top) {
			max = Math.max(max, getMaxRectangleInArray(array));
		}
		return max;
	}

	private int[][] getPreSumTop(int[][] matrix, int row, int col) {
		int[][] top = new int[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (matrix[i][j] == 0)
					continue;

				if (i == 0)
					top[i][j] = 1;
				else
					top[i][j] = top[i - 1][j] + 1;
			}
		}
		return top;
	}

	private int getMaxRectangleInArray(int[] array) {
		int max = 0;
		Deque<Integer> deque = new ArrayDeque<>();
		for (int i = 0; i <= array.length; i++) {
			int newValue = i < array.length ? array[i] : 0;
			while (!deque.isEmpty() && array[deque.peekFirst()] >= newValue) {
				int height = array[deque.pollFirst()];
				int left = deque.isEmpty() ? -1 : deque.peekFirst();
				int width = i - left - 1;
				int area = height * width;
				max = Math.max(max, area);
			}
			deque.offerFirst(i);
		}
		return max;
	}
}
