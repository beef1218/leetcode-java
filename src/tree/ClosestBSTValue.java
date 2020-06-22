package tree;

import utils.TreeNode;

/*
Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

Note:
Given target value is a floating point.
You are guaranteed to have only one unique value in the BST that is closest to the target.

Example:
Input: root = [4,2,5,1,3], target = 3.714286

    4
   / \
  2   5
 / \
1   3

Output: 4
 */
public class ClosestBSTValue {
	public int closestValue(TreeNode root, double target) {
		if (root == null) {
			return Integer.MIN_VALUE;
		}
		int result = root.val;
		while (root != null) {
			if (Math.abs(target - result) > Math.abs(target - root.val)) { // if we use an int to store the diff, it may overflow
				result = root.val;
			}
			if (root.val == target) {
				return result;
			} else if (root.val < target) {
				root = root.right;
			} else {
				root = root.left;
			}
		}
		return result;
	}
}
