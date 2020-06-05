package amazon;

import utils.TreeNode;

public class SerializeAndDeserializeBinaryTree {
	public static void main(String[] args) {
		String input = "1,2,3,null,null,4,5";
		SerializeAndDeserializeBinaryTree util = new SerializeAndDeserializeBinaryTree();
		util.deserialize(input);
	}

	public String serialize(TreeNode root) {
		StringBuilder sb = new StringBuilder();
		serialize(root, sb);
		return sb.toString();
	}

	private void serialize(TreeNode node, StringBuilder sb) {
		if (node == null) {
			sb.append(", null");
			return;
		}
		sb.append(", " + String.valueOf(node.val));
		serialize(node.left, sb);
		serialize(node.right, sb);
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		if (data == null || data.length() == 0) {
			return null;
		}
		String[] array = data.split(",");
		int[] index = new int[]{0};
		return deserialize(array, index);
	}

	private TreeNode deserialize(String[] array, int[] index) {
		if (index[0] >= array.length || String.valueOf(array[index[0]]).equals("null")) {
			return null;
		}
		TreeNode node = new TreeNode(Integer.parseInt(array[index[0]]));
		index[0]++;
		node.left = deserialize(array, index);
		node.right = deserialize(array, index);
		return node;
	}
}
