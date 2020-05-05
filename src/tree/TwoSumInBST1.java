package tree;

import java.util.Deque;

import utils.TreeNode;

/*
1. use two stacks to store elements.
	stack1 stores in in-order order
	stack2 stores in reversed in-order order
2. Compare elements from two stacks.
Terminate when the same element is found in both stacks
 */
public class TwoSumInBST1 {
	public boolean existSumBST(TreeNode root, int target) {
		if (root == null)
			return false;

		Deque<TreeNode> dequeL = new ArrayDeque<>();
		pushLeft(dequeL, root);
		Deque<TreeNode> dequeR = new ArrayDeque<>();
		pushRight(dequeR, root);
		while (!dequeL.isEmpty() && !dequeR.isEmpty()) {
			TreeNode nodeL = dequeL.peekFirst();
			TreeNode nodeR = dequeR.peekFirst();
			if (nodeL == nodeR)
				return false;

			if (nodeL.key + nodeR.key < target) {
				dequeL.pollFirst();
				pushLeft(dequeL, nodeL.right);
			} else if (nodeL.key + nodeR.key > target) {
				dequeR.pollFirst();
				pushRight(dequeR, nodeR.left);
			} else
				return true;
		}
		return false;
	}
	private void pushLeft(Deque<TreeNode> deque, TreeNode root) {
		while (root != null) {
			deque.offerFirst(root);
			root = root.left;
		}
	}
	private void pushRight(Deque<TreeNode> deque, TreeNode root) {
		while (root != null) {
			deque.offerFirst(root);
			root = root.right;
		}
	}
}