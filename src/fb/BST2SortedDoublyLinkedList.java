package fb;

import java.util.ArrayDeque;
import java.util.Deque;

import utils.Node;

/*
Convert a Binary Search Tree to a sorted Circular Doubly-Linked List in place.

You can think of the left and right pointers as synonymous to the predecessor and successor pointers in a doubly-linked list.
For a circular doubly linked list, the predecessor of the first element is the last element, and the successor of the last element is the first element.

We want to do the transformation in place. After the transformation, the left pointer of the tree node should point to its predecessor, and the right pointer should point to its successor.
You should return the pointer to the smallest element of the linked list.
 */
public class BST2SortedDoublyLinkedList {
	/*
	Method 1: Recursive with instance variables
	 */
	private Node first;
	private Node last;

	public Node treeToDoublyList(Node root) {
		if (root == null) {
			return null;
		}
		helper(root);
		first.left = last;
		last.right = first;
		return first;
	}

	private void helper(Node cur) {
		if (cur == null) {
			return;
		}
		helper(cur.left);
		if (first == null) {
			first = cur;
		}
		cur.left = last;
		if (last != null) {
			last.right = cur;
		}
		last = cur;
		helper(cur.right);
	}

	/*
	Method 2: Iterative
	 */
	public Node treeToDoublyList2(Node root) {
		if (root == null) {
			return null;
		}
		Deque<Node> deque = new ArrayDeque<>();
		pushLeft(deque, root);
		Node head = deque.pollFirst(); // store the smallest element as head
		Node prev = head;
		pushLeft(deque, head.right);
		while (!deque.isEmpty()) {
			Node cur = deque.pollFirst();
			pushLeft(deque, cur.right);
			prev.right = cur;
			cur.left = prev;
			prev = cur;
		}
		prev.right = head;
		head.left = prev;
		return head;
	}

	private void pushLeft(Deque<Node> deque, Node node) {
		while (node != null) {
			deque.offerFirst(node);
			node = node.left;
		}
	}
}
