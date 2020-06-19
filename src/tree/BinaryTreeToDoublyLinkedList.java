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
	// iterative
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


	// recursive
	private TreeNode first;
	private TreeNode last;

	public TreeNode treeToDoublyList(TreeNode root) {
		if (root == null) {
			return null;
		}
		helper(root);
		first.left = last;
		last.right = first;
		return first;
	}

	private void helper(TreeNode cur) {
		if (cur == null) {
			return;
		}
		helper(cur.left);
		if (first == null) {
			first = cur;
		}
		cur.left = last;
		if (last != null) {
			last.right = cur;
		}
		last = cur;
		helper(cur.right);
	}
}
