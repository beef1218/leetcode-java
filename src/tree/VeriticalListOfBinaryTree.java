package tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import utils.TreeNode;

/*
1. use a hashmap to store <colume, list of nodes>
2. do level order traversal, push nodes into map and keep track of min colume
3. use a wrapper class to store node and its colume

Time: O(n)
Space: O(n)
*/
public class VeriticalListOfBinaryTree {
	static class Wrapper {
		TreeNode node;
		int col;
		Wrapper(TreeNode node, int col) {
			this.node = node;
			this.col = col;
		}
	}
	public List<List<Integer>> verticalPrint(TreeNode root) {
		List<List<Integer>> result = new ArrayList<>();
		if (root == null)
			return result;

		Map<Integer, List<Integer>> map = new HashMap<>();
		int minCol = scanByLevel(root, map);
		for (int i = 0; i < map.size(); i++)
			result.add(new ArrayList<>());

		for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
			result.set(entry.getKey() - minCol, entry.getValue());
		}
		return result;
	}
	private int scanByLevel(TreeNode root, Map<Integer, List<Integer>> map) {
		Queue<Wrapper> queue = new ArrayDeque<>();
		queue.offer(new Wrapper(root, 0));
		int minCol = 0;
		while (!queue.isEmpty()) {
			Wrapper wrapper = queue.poll();
			TreeNode node = wrapper.node;
			int col = wrapper.col;
			minCol = Math.min(minCol, col);
			List<Integer> list = map.getOrDefault(col, new ArrayList<>());
			list.add(node.key);
			map.put(col, list);
			if (node.left != null)
				queue.offer(new Wrapper(node.left, col - 1));
			if (node.right != null)
				queue.offer(new Wrapper(node.right, col + 1));
		}
		return minCol;
	}
}
