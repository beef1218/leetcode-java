package tree;

import java.util.ArrayDeque;
import java.util.Deque;
import utils.TreeNode;
/*
use a stack.
1. push left into stack.
2. poll from stack as "prev", peek from stack as "next", connect prev and next.
terminate when stack is empty.

Time: O(n)
Space: O(height)
*/
public class BinaryTreeToDoublyLinkedList {
	public TreeNode toDoubleLinkedList(TreeNode root) {
		if (root == null)
			return null;

		Deque<TreeNode> deque = new ArrayDeque<>();
		pushLeft(deque, root);
		while (!deque.isEmpty()) {
			TreeNode prev = deque.pollFirst();
			pushLeft(deque, prev.right);
			TreeNode next = deque.peekFirst();
			prev.right = next;
			if (next != null)
				next.left = prev;
		}
		while (root.left != null)
			root = root.left;

		return root;
	}

	private void pushLeft(Deque<TreeNode> deque, TreeNode root) {
		while (root != null) {
			deque.offerFirst(root);
			root = root.left;
		}
	}
}
