package tree;

import java.util.ArrayDeque;
import java.util.Queue;
import utils.TreeNode;

/*
1. do level order traversal
2. when the generated child is x or y, store its parent.
3. check after each level. return true if both are generated at the same level and have different parent

Time: O(n)
Space: O(n)
*/
public class CousinsInBinaryTree {
	public boolean isCousins(TreeNode root, int x, int y) {
		if (root == null)
			return false;

		Queue<TreeNode> queue = new ArrayDeque<>();
		queue.offer(root);
		TreeNode p1 = null;
		TreeNode p2 = null;
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				TreeNode node = queue.poll();
				if (node.left != null) {
					if (node.left.val == x || node.left.val == y) {
						if (p1 == null)
							p1 = node;
						else if (p2 == null)
							p2 = node;
					}
					queue.offer(node.left);
				}
				if (node.right != null) {
					if (node.right.val == x || node.right.val == y) {
						if (p1 == null)
							p1 = node;
						else if (p2 == null)
							p2 = node;
					}
					queue.offer(node.right);
				}
			}
			if (p1 != null && p2 != null)
				return p1 != p2;
			else if (p1 != null || p2 != null)
				return false;
		}
		return false;
	}
}
