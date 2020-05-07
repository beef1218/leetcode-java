package tree;

import java.util.ArrayDeque;
import java.util.Queue;
import utils.TreeNode;

/*
Given a binary tree, write a function to get the maximum width of the given tree. The width of a tree is the maximum width among all levels.
The binary tree has the same structure as a full binary tree, but some nodes are null.

The width of one level is defined as the length between the end-nodes (the leftmost and right most non-null nodes in the level,
where the null nodes between the end-nodes are also counted into the length calculation.
 */
/*
1. level order traversal
2. Give each node an "index" value. root start with index = 0;
	when generate left, left's index = cur.index * 2;
	when generate right, right's index = cur.index * 2 + 1;
3. at each level, the width is last node index - first node index + 1. Update global max
 */
public class MaxWidthOfBinaryTree {
	static class Node {
		TreeNode treeNode;
		int index;
		Node(TreeNode treeNode, int index) {
			this.treeNode = treeNode;
			this.index = index;
		}
	}
	public int widthOfBinaryTree(TreeNode root) {
		if (root == null)
			return 0;

		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(new Node(root, 0));
		int max = 1;
		while (!queue.isEmpty()) {
			int size = queue.size();
			int left = queue.peek().index;
			int right = 0;
			for (int i = 0; i < size; i++) {
				Node node = queue.poll();
				if (i == size - 1)
					right = node.index;
				if (node.treeNode.left != null)
					queue.offer(new Node(node.treeNode.left, node.index * 2));
				if (node.treeNode.right != null)
					queue.offer(new Node(node.treeNode.right, node.index * 2 + 1));
			}
			max = Math.max(max, right - left + 1);
		}
		return max;
	}
}
