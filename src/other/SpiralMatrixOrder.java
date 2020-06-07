package other;

import java.util.ArrayList;
import java.util.List;
/*
Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
 */
public class SpiralMatrixOrder {
	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> result = new ArrayList<>();
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return result;
		}
		int row = matrix.length;
		int col = matrix[0].length;
		int top = 0;
		int bottom = row - 1;
		int left = 0;
		int right = col - 1;
		while (top < bottom && left < right) {
			for (int i = left; i < right; i++) {
				result.add(matrix[top][i]);
			}
			for (int i = top; i < bottom; i++) {
				result.add(matrix[i][right]);
			}
			for (int i = right; i > left; i--) {
				result.add(matrix[bottom][i]);
			}
			for (int i = bottom; i > top; i--) {
				result.add(matrix[i][left]);
			}
			top++;
			left++;
			bottom--;
			right--;
		}
		// 3 different base cases:
		// 1: nothing left
		// 2: one row left
		// 3: one col left
		if (top > bottom || left > right) {
			return result;
		}
		if (top == bottom) {
			for (int i = left; i <= right; i++) {
				result.add(matrix[top][i]);
			}
		} else {
			for (int i = top; i <= bottom; i++) {
				result.add(matrix[i][left]);
			}
		}
		return result;
	}
}
