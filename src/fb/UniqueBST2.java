package fb;

import java.util.ArrayList;
import java.util.List;

import utils.TreeNode;

/*
Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.

Example:
Input: 3
Output:
[
  [1,null,3,2],
  [3,2,null,1],
  [3,1,null,null,2],
  [2,1,3],
  [1,null,2,null,3]
]
Explanation:
The above output corresponds to the 5 unique BST's shown below:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
 */
/*
1. pick each element as root
2. use a helper function to return the list of unique trees with given range
3. nested for loop:
for each element as root: get list of unique trees from left rage; get list of unique trees from right range;
	for each pair of left unique tree and right unique tree, connect them. return the connected tree to parent
 */
public class UniqueBST2 {
	public List<TreeNode> generateTrees(int n) {
		if (n <= 0) {
			return new ArrayList<>();
		}
		return helper(1, n);
	}

	// returns list of constructed subtrees with numbers in range [left, right]
	private List<TreeNode> helper(int left, int right) {
		List<TreeNode> result = new ArrayList<>();
		if (left > right) {
			result.add(null); // need a null value, so that the upper level recursion can enter the nested for loops
			return result;
		}

		// pick i as root
		for (int i = left; i <= right; i++) {
			List<TreeNode> leftTrees = helper(left, i - 1);
			List<TreeNode> rightTrees = helper(i + 1, right);
			for (TreeNode leftTree : leftTrees) {
				for (TreeNode rightTree : rightTrees) {
					TreeNode cur = new TreeNode(i);
					cur.left = leftTree;
					cur.right = rightTree;
					result.add(cur);
				}
			}
		}
		return result;
	}
}
