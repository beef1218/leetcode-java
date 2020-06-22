package other;

import java.util.List;

/*
A binary matrix means that all elements are 0 or 1. For each individual row of the matrix, this row is sorted in non-decreasing order.

Given a row-sorted binary matrix binaryMatrix, return leftmost column index(0-indexed) with at least a 1 in it. If such index doesn't exist, return -1.

You can't access the Binary Matrix directly.  You may only access the matrix using a BinaryMatrix interface:

BinaryMatrix.get(row, col) returns the element of the matrix at index (row, col) (0-indexed).
BinaryMatrix.dimensions() returns a list of 2 elements [rows, cols], which means the matrix is rows * cols.
 */
/*
start from top right corner, find the first 1, then go down

public class LeftmostColumnWithAtLeastAOne {
	public <BinaryMatrix> int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
		List<Integer> dimensions = binaryMatrix.dimensions();
		int row = dimensions.get(0);
		int col = dimensions.get(1);
		int result = col;
		for (int r = 0; r < row; r++) {
			for (int c = result - 1; c >= 0; c--) {
				if (binaryMatrix.get(r, c) == 1) {
					result = c;
				} else {
					break;
				}
			}
		}
		return result == col ? -1 : result;
	}
}
*/