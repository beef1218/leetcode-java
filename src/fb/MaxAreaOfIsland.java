package fb;

import java.util.ArrayDeque;
import java.util.Queue;

/*
Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.)
You may assume all four edges of the grid are surrounded by water.
Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)

Example 1:

[[0,0,1,0,0,0,0,1,0,0,0,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,1,1,0,1,0,0,0,0,0,0,0,0],
 [0,1,0,0,1,1,0,0,1,0,1,0,0],
 [0,1,0,0,1,1,0,0,1,1,1,0,0],
 [0,0,0,0,0,0,0,0,0,0,1,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,0,0,0,0,0,0,1,1,0,0,0,0]]
Given the above grid, return 6. Note the answer is not 11, because the island must be connected 4-directionally.
 */
public class MaxAreaOfIsland {
	static int[][] DIRS = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

	/*
	Method1: BFS
	 */
	public int maxAreaOfIsland(int[][] grid) {
		if (grid == null || grid.length == 0 || grid[0].length == 0) {
			return 0;
		}
		boolean[][] visited = new boolean[grid.length][grid[0].length];
		int[] max = new int[]{0};
		for (int r = 0; r < grid.length; r++) {
			for (int c = 0; c < grid[0].length; c++) {
				if (grid[r][c] == 0 || visited[r][c]) {
					continue;
				}
				bfs(r, c, grid, visited, max);
			}
		}
		return max[0];
	}

	private void bfs(int r, int c, int[][] grid, boolean[][] visited, int[] max) {
		int row = grid.length;
		int col = grid[0].length;
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[]{r, c});
		visited[r][c] = true;
		int size = 0;
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			size++;
			for (int[] dir : DIRS) {
				int y = cur[0] + dir[0];
				int x = cur[1] + dir[1];
				if (y >= 0 && y < row && x >= 0 && x < col && grid[y][x] == 1 && !visited[y][x]) {
					visited[y][x] = true;
					queue.offer(new int[]{y, x});
				}
			}
		}
		max[0] = Math.max(max[0], size);
	}

	/*
	Method2: DFS
	 */
	public int maxAreaOfIsland2(int[][] grid) {
		if (grid == null || grid.length == 0 || grid[0].length == 0) {
			return 0;
		}
		boolean[][] visited = new boolean[grid.length][grid[0].length];
		int max = 0;
		for (int r = 0; r < grid.length; r++) {
			for (int c = 0; c < grid[0].length; c++) {
				if (grid[r][c] == 0 || visited[r][c]) {
					continue;
				}
				max = Math.max(max, dfs(r, c, grid, visited));
			}
		}
		return max;
	}

	private int dfs(int r, int c, int[][] grid, boolean[][] visited) {
		int row = grid.length;
		int col = grid[0].length;
		if (r < 0 || r >= row || c < 0 || c >= col || grid[r][c] == 0 || visited[r][c]) {
			return 0;
		}
		int size = 1;
		visited[r][c] = true;
		for (int[] dir : DIRS) {
			size += dfs(r + dir[0], c + dir[1], grid, visited);
		}
		return size;
	}
}
