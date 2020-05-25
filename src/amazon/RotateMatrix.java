package amazon;
/*
You are given an n x n 2D matrix representing an image.
Rotate the image by 90 degrees (clockwise).
 */
public class RotateMatrix {
    /*
    iterative
     */
    public void rotate1(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return;

        int length = matrix.length;
        int offset = 0;
        while (offset < length / 2) {
            int top = offset;
            int left = offset;
            int right = length - 1 - offset;
            int bottom = length - 1 - offset;
            for (int i = offset; i < right; i++) {
                int tmp = matrix[top][i];
                matrix[top][i] = matrix[length - 1 - i][left];
                matrix[length - 1 - i][left] = matrix[bottom][length - 1 - i];
                matrix[bottom][length - 1 - i] = matrix[i][right];
                matrix[i][right] = tmp;
            }
            offset++;
        }
    }

    /*
    recursive
     */
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0)
            return;

        rotate(matrix, 0);
    }
    private void rotate(int[][] m, int offset) {
        if (offset >= m.length / 2)
            return;

        int length = m.length;
        int top = offset;
        int bottom = length - 1 - offset;
        for (int i = offset; i <= length - 2 - offset; i++) {
            int tmp = m[top][i];
            m[top][i] = m[length - 1 - i][top];
            m[length - 1 - i][top] = m[bottom][length - 1 - i];
            m[bottom][length - 1 - i] = m[i][bottom];
            m[i][bottom] = tmp;
        }
        rotate(m, offset + 1);
    }
}
