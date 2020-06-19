package tree;

import utils.TreeNode;

/*
The thief has found himself a new place for his thievery again.
There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house.
After a tour, the smart thief realized that "all houses in this place forms a binary tree".
It will automatically contact the police if two directly-linked houses were broken into on the same night.

Determine the maximum amount of money the thief can rob tonight without alerting the police.
*/
/*
 1. from child: max1 (without child) and max2 (with child) in child subtree
 2. curMax1 = leftMax1 + rightLax1 + cur.key; curMax2 = max(leftMax1, leftMax2) + max(rightMax1, rightMax2)
 3. return [curMax1, curMax2]

final return: max(result[0], result[1])
 */
public class HouseRobber3 {
	public int rob(TreeNode root) {
		if (root == null)
			return 0;

		int[] result = helper(root);
		return Math.max(result[0], result[1]);
	}

	private int[] helper(TreeNode node) {
		if (node == null)
			return new int[]{0, 0};

		int[] left = helper(node.left);
		int[] right = helper(node.right);
		int includeCur = left[1] + right[1] + node.key;
		int excludeCur = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
		return new int[]{includeCur, excludeCur};
	}
}
