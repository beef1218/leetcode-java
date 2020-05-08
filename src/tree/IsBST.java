package tree;

import utils.TreeNode;

/*
 1. do pre-order traversal, carry min and max
 2. at each level, check whether cur is between min and max
 3. do recursion for child nodes, pass down min and max

 assume:
 1. duplicate nodes are not allowed in BST
 2. values of all nodes are between (-2 ^ 31, 2 ^ 31 - 1). If not, we will use long type for min and max
 */
public class IsBST {
	public boolean isBST(TreeNode root) {
		return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	private boolean isBST(TreeNode node, int min, int max) {
		if (node == null)
			return true;

		if (node.val <= min || node.val >= max)
			return false;

		return isBST(node.left, min, node.val) && isBST(node.right, node.val, max);
	}
}
