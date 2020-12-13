package fb;

import java.util.Arrays;

/*
Given two sparse matrices A and B, return the result of AB.
You may assume that A's column number is equal to B's row number.

Example:
Input:
A = [
  [ 1, 0, 0],
  [-1, 0, 3]
]

B = [
  [ 7, 0, 0 ],
  [ 0, 0, 0 ],
  [ 0, 0, 1 ]
]

Output:
     |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
                  | 0 0 1 |
 */
/*
1. since these are a sparse matrix, check the rows and cols that are all zeros.
2. during the calculation, for rows and cols that are all zeros, skip them.
 */
public class SparseMatrixMultiplication {
	public int[][] multiply(int[][] A, int[][] B) {
		boolean[] zeroRow = new boolean[A.length];
		Arrays.fill(zeroRow, true);
		boolean[] zeroCol = new boolean[B[0].length];
		Arrays.fill(zeroCol, true);
		for (int r = 0; r < A.length; r++) {
			for (int c = 0; c < A[0].length; c++) {
				if (A[r][c] != 0) {
					zeroRow[r] = false;
					break;
				}
			}
		}

		for (int c = 0; c < B[0].length; c++) {
			for (int r = 0; r < B.length; r++) {
				if (B[r][c] != 0) {
					zeroCol[c] = false;
					break;
				}
			}
		}

		int[][] result = new int[A.length][B[0].length];
		for (int r = 0; r < result.length; r++) {
			for (int c = 0; c < result[0].length; c++) {
				if (zeroRow[r] || zeroCol[c]) {
					continue;
				}
				int sum = 0;
				for (int i = 0; i < A[0].length; i++) {
					sum += A[r][i] * B[i][c];
				}
				result[r][c] = sum;
			}
		}
		return result;
	}
}
