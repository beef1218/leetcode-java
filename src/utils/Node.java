package utils;

import java.util.List;

public class Node {
	public int val;
	public List<Node> neighbors;
	public Node left;
	public Node right;

	public Node (int val) {
		this.val = val;
	}
}
