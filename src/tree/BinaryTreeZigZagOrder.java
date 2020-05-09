package tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import utils.TreeNode;
/*
 1. level order traversal
 2. use a deque. push nodes from left / right at altertive levels
 3. even level: pull from right. enqueue from left. right child -> left child
 odd level: pull from left. enqueue from right. left child -> right child
 */
public class BinaryTreeZigZagOrder {
	public List<Integer> zigZag(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		if (root == null)
			return result;

		Deque<TreeNode> deque = new ArrayDeque<>(); // deque: left is head. right is tail
		deque.offerFirst(root);
		int level = 0;
		while (!deque.isEmpty()) {
			int size = deque.size();
			for (int i = 0; i < size; i++) {
				if (level % 2 == 0) {
					TreeNode node = deque.pollLast();
					result.add(node.val);
					if (node.right != null)
						deque.offerFirst(node.right);
					if (node.left != null)
						deque.offerFirst(node.left);
				} else {
					TreeNode node = deque.pollFirst();
					result.add(node.val);
					if (node.left != null)
						deque.offerLast(node.left);
					if (node.right != null)
						deque.offerLast(node.right);
				}
			}
			level++;
		}
		return result;
	}
}