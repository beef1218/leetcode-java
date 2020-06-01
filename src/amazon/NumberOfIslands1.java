package amazon;

import java.util.ArrayDeque;
import java.util.Queue;

/*
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
You may assume all four edges of the grid are all surrounded by water.
 */
/*
bfs
1. for each 1, do bfs, use a boolean[][] to track visited spots, increase count
2. return count
Time: M x N
Space: M x N
*/
public class NumberOfIslands1 {
	static final int[][] DIRS = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	public int numIslands(char[][] grid) {
		if (grid == null || grid.length == 0 || grid[0].length == 0) {
			return 0;
		}
		int count = 0;
		boolean[][] visited = new boolean[grid.length][grid[0].length];
		for (int r = 0; r < grid.length; r++) {
			for (int c = 0; c < grid[0].length; c++) {
				if (grid[r][c] == '1' && !visited[r][c]) {
					count++;
					bfs(r, c, grid, visited);
				}
			}
		}
		return count;
	}
	private void bfs(int r, int c, char[][] grid, boolean[][] visited) {
		int row = grid.length;
		int col = grid[0].length;
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[]{r, c});
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			for (int[] dir : DIRS) {
				int y = cur[0] + dir[0];
				int x = cur[1] + dir[1];
				if (y >= 0 && y < row && x >= 0 && x < col && grid[y][x] == '1' && !visited[y][x]) {
					visited[y][x] = true;
					queue.offer(new int[]{y, x});
				}
			}
		}
	}
}
