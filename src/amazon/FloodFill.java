package amazon;

import java.util.ArrayDeque;
import java.util.Queue;

/*
An image is represented by a 2-D array of integers, each integer representing the pixel value of the image (from 0 to 65535).
Given a coordinate (sr, sc) representing the starting pixel (row and column) of the flood fill, and a pixel value newColor, "flood fill" the image.
To perform a "flood fill", consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel, plus any pixels connected 4-directionally to those pixels (also with the same color as the starting pixel), and so on. Replace the color of all of the aforementioned pixels with the newColor.
At the end, return the modified image.

Example 1:
Input:
image = [[1,1,1],[1,1,0],[1,0,1]]
sr = 1, sc = 1, newColor = 2
Output: [[2,2,2],[2,2,0],[2,0,1]]
 */
/*
1. do bfs with the starting point. use a 2d boolean array for dedup
2. find neighbors with the same color, add to queue, update dedup map.
3. poll from queue, update color.
terminate when queue is empty (bfs is done)
Time: O(n) - n is total number of points
Space: O(n)
 */
public class FloodFill {
	static int[][] dirs = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
		int row = image.length;
		int col = image[0].length;
		int oldColor = image[sr][sc];
		boolean[][] visited = new boolean[row][col];
		visited[sr][sc] = true;
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[]{sr, sc});
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			image[cur[0]][cur[1]] = newColor;
			for (int[] dir : dirs) {
				int r = cur[0] + dir[0];
				int c = cur[1] + dir[1];
				if (r >= 0 && r < row && c >= 0 && c < col && !visited[r][c] && image[r][c] == oldColor) {
					visited[r][c] = true;
					queue.offer(new int[]{r, c});
				}
			}
		}
		return image;
	}
}
