package fb;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

import javafx.util.Pair;
import utils.TreeNode;

/*
1. use a hashmap to store <colume, list of nodes>
2. do level order traversal, push nodes into map and keep track of min colume
3. use a wrapper class to store node and its colume

Time: O(n)
Space: O(n)
*/
public class VeriticalListOfBinaryTree {
	public List<List<Integer>> verticalTraversal1(TreeNode root) {
		List<List<Integer>> result = new ArrayList<>();
		if (root == null) {
			return result;
		}
		int[] minCol = new int[]{0};
		Map<Integer, List<Integer>> colLists = new HashMap<>();
		helper(root, minCol, colLists);
		for (int i = 0; i < colLists.size(); i++) {
			result.add(new ArrayList<>());
		}
		for (Map.Entry<Integer, List<Integer>> entry : colLists.entrySet()) {
			result.set(entry.getKey() - minCol[0], entry.getValue());
		}
		return result;
	}

	private void helper(TreeNode root, int[] minCol, Map<Integer, List<Integer>> colLists) {
		Queue<Pair<TreeNode, Integer>> queue = new ArrayDeque<>();
		queue.offer(new Pair<>(root, 0));
		while (!queue.isEmpty()) {
			Pair<TreeNode, Integer> cur = queue.poll();
			TreeNode node = cur.getKey();
			int col = cur.getValue();
			minCol[0] = Math.min(minCol[0], col);
			colLists.computeIfAbsent(col, (k) -> new ArrayList<>()).add(node.val);
			if (node.left != null) {
				queue.offer(new Pair<>(node.left, col - 1));
			}
			if (node.right != null) {
				queue.offer(new Pair<>(node.right, col + 1));
			}
		}
	}

	/*
	Method 2: Using TreeMap instead of hashmap to simplify the key ordering.
	Time complexity changes from O(n) -> O(n * logn)
	 */

	static class Node {
		TreeNode treeNode;
		int col;

		Node(TreeNode treeNode, int col) {
			this.treeNode = treeNode;
			this.col = col;
		}
	}

	public List<List<Integer>> verticalTraversal2(TreeNode root) {
		if (root == null) {
			return new ArrayList<>();
		}
		Map<Integer, List<Integer>> map = new TreeMap<>();
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(new Node(root, 0));
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			TreeNode treeNode = cur.treeNode;
			int curCol = cur.col;
			map.computeIfAbsent(curCol, (a) -> new ArrayList<>()).add(treeNode.val);
			if (treeNode.left != null) {
				queue.offer(new Node(treeNode.left, curCol - 1));
			}
			if (treeNode.right != null) {
				queue.offer(new Node(treeNode.right, curCol + 1));
			}
		}
		List<List<Integer>> result = new ArrayList<>(map.values());
		return result;
	}

	/*
	Requirement changed to: for nodes at the same col and same row, sort by their values

	1. do dfs to add all nodes into a list, store <col, row, node.val>
	2. sort them by col -> row -> val
	3. add to result
	 */

	static class Node2 implements Comparable<Node2> {
		int col;
		int row;
		int val;

		Node2(int col, int row, int val) {
			this.col = col;
			this.row = row;
			this.val = val;
		}

		@Override
		public int compareTo(Node2 node2) {
			if (col != node2.col) {
				return Integer.valueOf(col).compareTo(node2.col);
			}
			if (row != node2.row) {
				return Integer.valueOf(row).compareTo(node2.row);
			}
			return Integer.valueOf(val).compareTo(node2.val);
		}
	}

	public List<List<Integer>> verticalTraversal(TreeNode root) {
		List<List<Integer>> result = new ArrayList<>();
		if (root == null) {
			return result;
		}
		List<Node2> list = new ArrayList<>();
		dfs(root, 0, 0, list);
		Collections.sort(list);
		int minCol = list.get(0).col;
		int colCount = list.get(list.size() - 1).col - minCol + 1;
		for (int i = 0; i < colCount; i++) {
			result.add(new ArrayList<>());
		}
		for (Node2 node : list) {
			result.get(node.col - minCol).add(node.val);
		}
		return result;
	}

	private void dfs(TreeNode node, int col, int row, List<Node2> list) {
		if (node == null) {
			return;
		}
		list.add(new Node2(col, row, node.val));
		dfs(node.left, col - 1, row + 1, list);
		dfs(node.right, col + 1, row + 1, list);
	}
}
