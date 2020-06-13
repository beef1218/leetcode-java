package tree;

import java.util.ArrayList;
import java.util.List;

import utils.TreeNode;

/*
Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

Example:

Input: [1,2,3,null,5,null,4]
Output: [1, 3, 4]
Explanation:

   1
 /   \
2     3
 \     \
  5     4
 */
/*
Method1 (BFS):
1. do level order traversal from right to left.
2. at each level, add the first element into the result list

Method2 (DFS):
1. Do pre-order traversal with a result list and a variable "depth" - the depth of the current node
2. At each recursion, compare result list size and depth. The result list size means the number of levels we have visited.
2.1. if depth > result list size: cur node is at a deeper level and we have not visited this level, add cur node to result list.
2.2. else: we already visited this level, do nothing.
 */
public class BinaryTreeRightSideView {
	public List<Integer> rightSideView(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		dfs(root, 1, result);
		return result;
	}

	private void dfs(TreeNode node, int depth, List<Integer> result) {
		if (node == null) {
			return;
		}
		if (depth > result.size()) {
			result.add(node.val);
		}
		dfs(node.right, depth + 1, result);
		dfs(node.left, depth + 1, result);
	}
}
