package bfs;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

import javafx.util.Pair;

/*
In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].
A knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.
Return the minimum number of steps needed to move the knight to the square [x, y].  It is guaranteed the answer exists.

Example 1:
Input: x = 2, y = 1
Output: 1
Explanation: [0, 0] → [2, 1]

Example 2:
Input: x = 5, y = 5
Output: 4
Explanation: [0, 0] → [2, 1] → [4, 2] → [3, 4] → [5, 5]

Constraints:
|x| + |y| <= 300
 */
/*
1. do bfs
2. when steps are more than 2, only continue on top right quarter of the board because the board is symmetric
 */
public class MinKnightMoves {
	static int[][] DIRS = new int[][]{{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}};

	public int minKnightMoves(int x, int y) {
		x = Math.abs(x);
		y = Math.abs(y);
		int step = 0;
		Set<Pair<Integer, Integer>> visited = new HashSet<>();
		visited.add(new Pair<>(0, 0));
		Queue<Pair<Integer, Integer>> queue = new ArrayDeque<>();
		queue.offer(new Pair<>(0, 0));
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				Pair<Integer, Integer> cur = queue.poll();
				int r = cur.getKey();
				int c = cur.getValue();
				if (r == x && c == y) {
					return step;
				}
				if (r > 310 || c > 310) { // terminate after 310 since |x| + |y| <= 300
					return -1;
				}
				if (step > 2 && (r < 0 || c < 0)) {
					continue;
				}
				for (int[] dir : DIRS) {
					Pair<Integer, Integer> next = new Pair<>(r + dir[0], c + dir[1]);
					if (visited.add(next)) {
						queue.offer(next);
					}
				}
			}
			step++;
		}
		return -1;
	}
}
