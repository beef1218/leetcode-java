package linkedlist;

import utils.ListNode;

/*
Given a linked list, remove the n-th node from the end of list and return its head.

Example:
Given linked list: 1->2->3->4->5, and n = 2.
After removing the second node from the end, the linked list becomes 1->2->3->5.
 */
public class RemoveNthNodeFromEndOfList {
	/*
	Recursive solution making use of the call stacks
	1. Use an instance variable count
	2. Do recursion to reach the end of list
	3. When return to each recursion call, increase count. When count == N, remove cur node by return its next node to parent
	(connected as the next node of the parent node)
	 */
	private int count = 0;

	public ListNode removeNthFromEnd(ListNode head, int n) {
		if (head == null) {
			return null;
		}
		head.next = removeNthFromEnd(head.next, n);
		count++;
		if (count == n) {
			return head.next;
		}
		return head;
	}

	/*
	Iterative solution with slow and fast runners
	 */
	public ListNode removeNthFromEnd2(ListNode head, int n) {
		// assume n is within the range of length
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode slow = dummy;
		ListNode fast = dummy;
		for (int i = 0; i <= n; i++) {
			fast = fast.next;
		}
		while (fast != null) {
			fast = fast.next;
			slow = slow.next;
		}
		slow.next = slow.next.next;
		return dummy.next;
	}
}
