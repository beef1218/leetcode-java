package fb;

import java.util.ArrayDeque;
import java.util.Queue;

/*
You want to build a house on an empty land which reaches all buildings in the shortest amount of distance.
You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:

Each 0 marks an empty land which you can pass by freely.
Each 1 marks a building which you cannot pass through.
Each 2 marks an obstacle which you cannot pass through.

Example:

Input: [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]

1 - 0 - 2 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0

Output: 7

Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2),
             the point (1,2) is an ideal empty land to build a house, as the total
             travel distance of 3+3+1=7 is minimal. So return 7.
Note:
There will be at least one building. If it is not possible to build such house according to the above rules, return -1.
 */
/*
BFS
1. use a 2d array to store distance
2. from each building, do bfs, add distance of this building to the 2d array
3. find the spot in the 2d array with smallest value

Since we need to keep track of the number of buildings a spot can reach, we create our own Node class.

K: number of buildings
Matrix: M * N
Time: K * M * N
Space: M * N
*/
public class ShortestDistanceFromAllBuildings {
	public static void main(String[] args) {
		ShortestDistanceFromAllBuildings solution = new ShortestDistanceFromAllBuildings();
		int[][] grid = new int[][]{{1, 0, 2, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}};
		System.out.println(solution.shortestDistance(grid));
	}

	static class Node {
		int count = 0; // number of buildings this node can reach
		int value = 0; // total distance to all buildings from this node
	}

	static int[][] DIRS = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

	public int shortestDistance(int[][] grid) {
		// initialize the 2d array
		Node[][] dist = new Node[grid.length][grid[0].length];
		for (int r = 0; r < dist.length; r++) {
			for (int c = 0; c < dist[0].length; c++) {
				dist[r][c] = new Node();
			}
		}
		int buildingCount = 0;
		for (int r = 0; r < grid.length; r++) {
			for (int c = 0; c < grid[0].length; c++) {
				if (grid[r][c] == 1) {
					buildingCount++;
					addDistance(r, c, grid, dist); // for each building, do bfs to all empty spots on the grid
				}
			}
		}
		int result = Integer.MAX_VALUE;
		for (int r = 0; r < dist.length; r++) {
			for (int c = 0; c < grid[0].length; c++) {
				if (dist[r][c].count == buildingCount && grid[r][c] == 0 && dist[r][c].value < result) {
					result = dist[r][c].value;
				}
			}
		}
		return result == Integer.MAX_VALUE ? -1 : result;
	}

	private void addDistance(int r, int c, int[][] grid, Node[][] dist) {
		int row = grid.length;
		int col = grid[0].length;
		boolean[][] visited = new boolean[row][col];
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[]{r, c});
		visited[r][c] = true;
		int step = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int[] cur = queue.poll();
				dist[cur[0]][cur[1]].count++;
				dist[cur[0]][cur[1]].value += step;
				for (int[] dir : DIRS) {
					int y = cur[0] + dir[0];
					int x = cur[1] + dir[1];
					if (y >= 0 && y < row && x >= 0 && x < col && !visited[y][x] && grid[y][x] == 0) {
						visited[y][x] = true;
						queue.offer(new int[]{y, x});
					}
				}
			}
			step++;
		}
	}
}
