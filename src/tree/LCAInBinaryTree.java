package tree;

import utils.TreeNode;

/**
 * Description: Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 * Assume both nodes exist in the tree.
 *
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 1. if root is null, return null
 2. if root is p or q, return root
 3. check left and right result
 3a. if both are not null, return root
 3b. if one is not null, return that one
 3c. both are null, return null
 */
class LCAInBinaryTree {
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null || root == p || root == q)
			return root;

		TreeNode left = lowestCommonAncestor(root.left, p, q);
		TreeNode right = lowestCommonAncestor(root.right, p, q);
		if (left != null && right != null)
			return root;

		return left == null ? right : left;
	}
}