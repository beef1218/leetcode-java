package tree;

import utils.TreeNode;

/*
Given a binary tree, flatten it to a linked list in-place.

For example, given the following tree:

    1
   / \
  2   5
 / \   \
3   4   6
The flattened tree should look like:

1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6
 */
/*
1. use a pointer to store the previous node, since this variable will be updated in the inner recursion calls, need to use an array with length of 1
2. do post-order traversal, point right to prev, point left to null, update prev pointer.
 */
public class FlattenBinaryTree {
	public void flatten(TreeNode root) {
		if (root == null) {
			return;
		}
		TreeNode[] prev = new TreeNode[1];
		helper(root, prev);
	}

	private void helper(TreeNode node, TreeNode[] prev) {
		if (node == null) {
			return;
		}
		helper(node.right, prev);
		helper(node.left, prev);
		node.right = prev[0];
		node.left = null;
		prev[0] = node;
	}
}
