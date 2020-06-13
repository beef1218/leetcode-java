package dp;

/*
Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

Range Sum Query 2D
The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.

Example:
Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
sumRegion(1, 1, 2, 2) -> 11
sumRegion(1, 2, 2, 4) -> 12
Note:
You may assume that the matrix does not change.
There are many calls to sumRegion function.
You may assume that row1 ≤ row2 and col1 ≤ col2.
 */
public class MatrixSumQuery {
	private int[][] prefixSum;
	private int row;
	private int col;

	public MatrixSumQuery(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
		row = matrix.length;
		col = matrix[0].length;
		prefixSum = new int[row + 1][col + 1];
		generatePrefixSum(matrix);
	}

	public int sumRegion(int row1, int col1, int row2, int col2) {
		return prefixSum[row2 + 1][col2 + 1] - prefixSum[row1][col2 + 1] - prefixSum[row2 + 1][col1] + prefixSum[row1][col1];
	}

	private void generatePrefixSum(int[][] matrix) {
		for (int r = 1; r <= row; r++) {
			for (int c = 1; c <= col; c++) {
				prefixSum[r][c] = prefixSum[r - 1][c] + prefixSum[r][c - 1] - prefixSum[r - 1][c - 1] + matrix[r - 1][c - 1];
			}
		}
	}
}
