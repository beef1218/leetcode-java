package bfs;

import static junit.framework.TestCase.assertEquals;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

import org.junit.Test;

/*
You are asked to cut off trees in a forest for a golf event. The forest is represented as a non-negative 2D map, in this map:

0 represents the obstacle can't be reached.
1 represents the ground can be walked through.
The place with number bigger than 1 represents a tree can be walked through, and this positive number represents the tree's height.
In one step you can walk in any of the four directions top, bottom, left and right also when standing in a point which is a tree you can decide whether or not to cut off the tree.

You are asked to cut off all the trees in this forest in the order of tree's height - always cut off the tree with lowest height first. And after cutting, the original place has the tree will become a grass (value 1).

You will start from the point (0, 0) and you should output the minimum steps you need to walk to cut off all the trees. If you can't cut off all the trees, output -1 in that situation.

You are guaranteed that no two trees have the same height and there is at least one tree needs to be cut off.
 */


/*
1. do bfs from [0,0], no some trees cannot be reached, return -1.
2. for each tree, create own object (height, row, col), sort by height.
3. linear scan the sorted array. add distance.
return total distance

forest size: MxN
Time: M * N * log(M * N)
Space: M * N
*/

// did not pass on Leetcode
public class CutOffTreesForGolfEvent {

	@Test
	public void TestCutOffTreesForGolfEvent() {
		CutOffTreesForGolfEvent solution = new CutOffTreesForGolfEvent();
		List<List<Integer>> input = new ArrayList<>();
		input.add(new ArrayList<>());
		input.add(new ArrayList<>());
		input.add(new ArrayList<>());
		input.get(0).add(1);
		input.get(0).add(2);
		input.get(0).add(3);
		input.get(1).add(0);
		input.get(1).add(0);
		input.get(1).add(4);
		input.get(2).add(7);
		input.get(2).add(6);
		input.get(2).add(5);
		assertEquals(6, solution.cutOffTree(input));

	}

	static class Tree {
		int height;
		int r;
		int c;
		Tree(int height, int r, int c) {
			this.height = height;
			this.r = r;
			this.c = c;
		}
	}
	public int cutOffTree(List<List<Integer>> forest) {
		if (forest == null || forest.size() == 0 || forest.get(0).size() == 0 || cannotReach(forest))
			return -1;

		List<Tree> trees = new ArrayList<>();
		for (int r = 0; r < forest.size(); r++) {
			for (int c = 0; c < forest.get(0).size(); c++) {
				int height = forest.get(r).get(c);
				if (height <= 1)
					continue;

				trees.add(new Tree(height, r, c));
			}
		}
		Collections.sort(trees, (a, b) -> a.height - b.height);
		return getDistance(trees);
	}
	private boolean cannotReach(List<List<Integer>> forest) {
		int row = forest.size();
		int col = forest.get(0).size();
		int[][] dirs = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
		boolean[][] visited = new boolean[row][col];
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[]{0, 0});
		visited[0][0] = true;
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			for (int[] dir : dirs) {
				int r = cur[0] + dir[0];
				int c = cur[1] + dir[1];
				if (r >= 0 && r < row && c >= 0 && c < col && !visited[r][c] && forest.get(r).get(c) != 0) {
					visited[r][c] = true;
					queue.offer(new int[]{r, c});
				}
			}
		}
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				if (forest.get(r).get(c) > 1 && !visited[r][c])
					return true;
			}
		}
		return false;
	}
	private int getDistance(List<Tree> trees) {
		int dist = 0;
		for (int i = 1; i < trees.size(); i++) {
			Tree prev = trees.get(i - 1);
			Tree next = trees.get(i);
			dist += Math.abs(next.r - prev.r) + Math.abs(next.c - prev.c);
		}
		return dist;
	}
}
