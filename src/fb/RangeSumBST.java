package fb;

import utils.TreeNode;

/*
Given the root node of a binary search tree, return the sum of values of all nodes with value between L and R (inclusive).
The binary search tree is guaranteed to have unique values.

Example 1:
Input: root = [10,5,15,3,7,null,18], L = 7, R = 15
Output: 32
 */
public class RangeSumBST {
	public int rangeSumBST(TreeNode root, int L, int R) {
		if (root == null) {
			return 0;
		}
		int[] sum = new int[]{0};
		helper(root, L, R, sum);
		return sum[0];
	}

	private void helper(TreeNode node, int L, int R, int[] sum) {
		if (node == null) {
			return;
		}
		if (node.val >= L && node.val <= R) {
			sum[0] += node.val;
		}
		if (node.val < R) {
			helper(node.right, L, R, sum);
		}
		if (node.val > L) {
			helper(node.left, L, R, sum);
		}
	}
}
