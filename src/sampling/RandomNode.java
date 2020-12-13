package sampling;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Random;

import utils.TreeNode;

/*
1. create an iterator for a binary tree. hasNext(), next()
2. reservoir sampling:
	a. store a sample variable. keep track of the total nodes we have visited. (count)
	b. for each new node, 1 / count chance we will replace the sample with it

Time: O(n)
Space: O(height)
 */
public class RandomNode {
	public TreeNode getRandomNode(TreeNode root) {
		if (root == null) {
			return null;
		}
		Iterator<TreeNode> treeIterator = new TreeIterator(root);
		int count = 1;
		TreeNode result = root;
		Random ranGen = new Random();
		while (treeIterator.hasNext()) {
			TreeNode node = treeIterator.next();
			count++;
			int random = ranGen.nextInt(count);
			if (random == 0) {
				result = node;
			}
		}
		return result;
	}
}

class TreeIterator implements Iterator<TreeNode> {

	private Deque<TreeNode> deque;

	public TreeIterator(TreeNode root) {
		deque = new ArrayDeque<>();
		pushLeft(root);
	}

	@Override
	public boolean hasNext() {
		return !deque.isEmpty();
	}

	@Override
	public TreeNode next() {
		if (!hasNext()) {
			return null;
		}
		TreeNode node = deque.pollFirst();
		pushLeft(node.right);
		return node;
	}

	private void pushLeft(TreeNode node) {
		while (node != null) {
			deque.offerFirst(node);
			node = node.left;
		}
	}
}
