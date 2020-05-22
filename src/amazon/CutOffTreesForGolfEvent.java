package amazon;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

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
1. find all trees, create own class (height, location), sort by tree height
2. add 0,0 to the front of the tree list, get distance between each pair of trees
3. if a tree cannot be reached, return -1; otherwise, return sum of steps

Time: M x N x log(MxN)
Space: M x N
*/

public class CutOffTreesForGolfEvent {
	static class Tree implements Comparable<Tree>{
		int height;
		int r;
		int c;
		Tree (int height, int r, int c) {
			this.height = height;
			this.r = r;
			this.c = c;
		}
		@Override
		public int compareTo(Tree tree2) {
			return Integer.valueOf(height).compareTo(tree2.height);
		}
	}
	static int[][] dirs = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	public int cutOffTree(List<List<Integer>> forest) {
		if (forest == null || forest.size() == 0 || forest.get(0).size() == 0)
			return 0;

		List<Tree> trees = getTrees(forest);
		trees.add(0, new Tree(0, 0, 0)); // add starting location to the front of the list
		int result = 0;
		for (int i = 0; i < trees.size() - 1; i++) {
			int dist = getDist(trees.get(i), trees.get(i + 1), forest); // get distance between each two trees
			if (dist == -1)
				return -1;

			result += dist;
		}
		return result;
	}
	private int getDist(Tree from, Tree to, List<List<Integer>> forest) {
		int row = forest.size();
		int col = forest.get(0).size();
		int step = 0;
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[]{from.r, from.c});
		boolean[][] visited = new boolean[row][col];
		visited[from.r][from.c] = true;
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int[] cur = queue.poll();
				if (cur[0] == to.r && cur[1] == to.c)
					return step;

				for (int[] dir : dirs) {
					int r = cur[0] + dir[0];
					int c = cur[1] + dir[1];
					if (r >= 0 && r < row && c >= 0 && c < col && !visited[r][c] && forest.get(r).get(c) != 0) {
						queue.offer(new int[]{r, c});
						visited[r][c] = true;
					}
				}
			}
			step++;
		}
		return -1;
	}
	private List<Tree> getTrees(List<List<Integer>> forest) {
		List<Tree> trees = new ArrayList<>();
		for (int i = 0; i < forest.size(); i++) {
			for (int j = 0; j < forest.get(0).size(); j++) {
				if (forest.get(i).get(j) > 1)
					trees.add(new Tree(forest.get(i).get(j), i, j));
			}
		}
		Collections.sort(trees);
		return trees;
	}
}
