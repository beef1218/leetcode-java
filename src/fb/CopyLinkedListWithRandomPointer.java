package fb;

import java.util.HashMap;
import java.util.Map;

/*
A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
Return a deep copy of the list.
 */
/*
1. use a hashmap to store <node, copy>
2. copy head, add to map
3. for each node, if node.next != null, copy next if needed, connect next pointer
4. for each node, if node.random != null, copy random if needed, connect random pointer
return head's copy
 */
public class CopyLinkedListWithRandomPointer {
	static class Node {
		int val;
		Node next;
		Node random;
		Node (int val) {
			this.val = val;
		}
	}
	public Node copyRandomList(Node head) {
		if (head == null)
			return null;

		Map<Node, Node> map = new HashMap<>();
		map.put(head, new Node(head.val));
		Node cur = head;
		while (cur != null) {
			if (cur.next != null) {
				Node nextCopy = map.getOrDefault(cur.next, new Node(cur.next.val));
				map.put(cur.next, nextCopy);
				map.get(cur).next = nextCopy;
			}
			if (cur.random != null) {
				Node randomCopy = map.getOrDefault(cur.random, new Node(cur.random.val));
				map.put(cur.random, randomCopy);
				map.get(cur).random = randomCopy;
			}
			cur = cur.next;
		}
		return map.get(head);
	}
}
