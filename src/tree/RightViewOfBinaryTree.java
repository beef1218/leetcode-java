package tree;

import java.util.ArrayList;
import java.util.List;

import utils.TreeNode;
/*
Method 1: (bfs1)
Level order traversal, only print the right most node for each level.

Method2: (dfs)
1. Similar to in order traversal but traverse right child before left child.
2. Carry a "depth" variable so that we know the depth of the current node.
3. The size of the result list means the right view of number of levels we have found.
When the current depth is greater than result list size, that means we are at a new level and need to add the current node to the result.
*/

public class RightViewOfBinaryTree {
	public List<Integer> rightView(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		if (root == null) {
			return result;
		}
		helper(root, 1, result);
		return result;
	}
	private void helper(TreeNode node, int depth, List<Integer> result) {
		if (node == null) {
			return;
		}
		if (depth > result.size()) {
			result.add(node.key);
		}
		helper(node.right, depth + 1, result);
		helper(node.left, depth + 1, result);
	}
}
