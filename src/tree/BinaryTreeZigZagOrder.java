package tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import utils.TreeNode;
/*
 1. level order traversal
 2. use a deque. push nodes from left / right at alternative levels
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
				TreeNode node;
				if (level % 2 == 0) {
					node = deque.pollLast();
					if (node.right != null)
						deque.offerFirst(node.right);
					if (node.left != null)
						deque.offerFirst(node.left);
				} else {
					node = deque.pollFirst();
					if (node.left != null)
						deque.offerLast(node.left);
					if (node.right != null)
						deque.offerLast(node.right);
				}
				result.add(node.val);
			}
			level++;
		}
		return result;
	}
}
