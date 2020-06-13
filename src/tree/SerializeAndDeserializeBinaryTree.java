package tree;

import utils.TreeNode;
/*
In-order traversal
You may serialize the following tree:

    1
   / \
  2   3
     / \
    4   5

as "[1,2,null,null,3,4,5]"
 */

public class SerializeAndDeserializeBinaryTree {
	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {
		if (root == null) {
			return "null";
		}
		StringBuilder sb = new StringBuilder();
		serializeHelper(root, sb);
		return sb.toString();
	}

	private void serializeHelper(TreeNode node, StringBuilder sb) {
		if (node == null) {
			sb.append(",null");
			return;
		}
		if (sb.length() == 0) {
			sb.append(node.val);
		} else {
			sb.append("," + node.val);
		}
		serializeHelper(node.left, sb);
		serializeHelper(node.right, sb);
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		if (data == null || data.length() == 0) {
			return null;
		}
		String[] array = data.split(",");
		int[] index = new int[]{0};
		return deserilizeHelper(array, index);
	}

	private TreeNode deserilizeHelper(String[] data, int[] index) {
		if (index[0] >= data.length || data[index[0]].equals("null")) {
			index[0]++;
			return null;
		}
		TreeNode node = new TreeNode(Integer.parseInt(data[index[0]]));
		index[0]++;
		node.left = deserilizeHelper(data, index);
		node.right = deserilizeHelper(data, index);
		return node;
	}
}
