package amazon;

import utils.TreeNode;

/*
Get the max path sum between any two nodes. At least one node needs to be included
3 steps:
 1. from child: max path sum that ends at the child node
 2. get left, get right, sum = cur.val + max(left, 0) + max(right, 0); update global max
 3. return cur.val + max(max(left, 0), max(right, 0))
 */
public class BinaryTreeMaxPathSum {
	public int maxPathSum(TreeNode root) {
		if (root == null)
			return 0;

		int[] max = new int[]{root.val}; // initialize it with root.val because at least one node needs to be included
		helper(root, max);
		return max[0];
	}
	private int helper(TreeNode node, int[] max) {
		if (node == null)
			return 0;

		int left = Math.max(0, helper(node.left, max));
		int right = Math.max(0, helper(node.right, max));
		max[0] = Math.max(max[0], left + right + node.val);
		return node.val + Math.max(left, right);
	}
}
