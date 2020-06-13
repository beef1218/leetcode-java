package fb;

import java.util.HashMap;
import java.util.Map;

import utils.Node;


public class CloneGraph {
	/*
	1. use a hashmap to store <node : copy>
	2. helper function to return the copy of a node. Do it in one pass recursively.
	 */
	public Node cloneGraph1(Node node) {
		if (node == null) {
			return null;
		}
		Map<Node, Node> map = new HashMap<>();
		return helper(node, map);
	}

	private Node helper(Node node, Map<Node, Node> map) {
		if (map.containsKey(node)) {
			return map.get(node);
		}
		Node copy = new Node(node.val);
		map.put(node, copy);
		for (Node nei : node.neighbors) {
			copy.neighbors.add(helper(nei, map));
		}
		return copy;
	}

	/*
	1. use a hashmap to store <node: copy>
	2. recursively create copy of all the nodes and add to map
	3. add the neighbors reference
	 */
	public Node cloneGraph2(Node node) {
		if (node == null) {
			return node;
		}
		Map<Node, Node> map = new HashMap<>();
		addToMap(node, map);
		for (Map.Entry<Node, Node> entry : map.entrySet()) {
			for (Node nei : entry.getKey().neighbors) {
				entry.getValue().neighbors.add(map.get(nei));
			}
		}
		return map.get(node);
	}

	private void addToMap(Node node, Map<Node, Node> map) {
		if (map.containsKey(node)) {
			return;
		}
		map.put(node, new Node(node.val));
		for (Node nei : node.neighbors) {
			addToMap(nei, map);
		}
	}
}
