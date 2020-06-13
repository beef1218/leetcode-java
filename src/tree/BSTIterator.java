package tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

import utils.TreeNode;

/*
Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.
 */
/*
 1. use a stack, push node into stack, go left... repeat. At the end, stack top is the smallest element
 2. next(): pop from stack, go to right of the popped node, push that to stack, go left...
 3. hasNext(): return stack is not empty
 */
public class BSTIterator implements Iterator<Integer> {
	private Deque<TreeNode> deque;

	public BSTIterator(TreeNode root) {
		deque = new ArrayDeque<>();
		pushLeft(root);
	}

	/**
	 * @return the next smallest number
	 */
	public Integer next() {
		if (!hasNext()) {
			return null;
		}
		TreeNode node = deque.pollFirst();
		pushLeft(node.right);
		return node.val;
	}

	/**
	 * @return whether we have a next smallest number
	 */
	public boolean hasNext() {
		return !deque.isEmpty();
	}

	private void pushLeft(TreeNode node) {
		while (node != null) {
			deque.offerFirst(node);
			node = node.left;
		}
	}
}
