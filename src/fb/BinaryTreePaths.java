package fb;

import java.util.ArrayList;
import java.util.List;

import utils.TreeNode;

/*
Given a binary tree, return all root-to-leaf paths.
Note: A leaf is a node with no children.

Example:
Input:

   1
 /   \
2     3
 \
  5

Output: ["1->2->5", "1->3"]

Explanation: All root-to-leaf paths are: 1->2->5, 1->3
 */
/*
Do a pre-order traversal, carry the current path. When reached a leaf, add current path to result
Time: O(n)
Space: O(height)
 */
public class BinaryTreePaths {
	public List<String> binaryTreePaths(TreeNode root) {
		List<String> result = new ArrayList<>();
		if (root == null) {
			return result;
		}
		dfs(root, new ArrayList<>(), result);
		return result;
	}

	private void dfs(TreeNode node, List<Integer> cur, List<String> result) {
		cur.add(node.val);
		if (node.left == null && node.right == null) {
			addToResult(cur, result);
		} else {
			if (node.left != null) {
				dfs(node.left, cur, result);
			}
			if (node.right != null) {
				dfs(node.right, cur, result);
			}
		}
		cur.remove(cur.size() - 1);
	}

	private void addToResult(List<Integer> cur, List<String> result) {
		StringBuilder sb = new StringBuilder();
		for (int num : cur) {
			if (sb.length() != 0) {
				sb.append("->");
			}
			sb.append(num);
		}
		result.add(sb.toString());
	}
}
