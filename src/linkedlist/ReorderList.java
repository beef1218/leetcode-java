package linkedlist;

import utils.ListNode;

/*
Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You may not modify the values in the list's nodes, only nodes itself may be changed.

Example 1:
Given 1->2->3->4, reorder it to 1->4->2->3.

Example 2:
Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
 */
/*
1. find mid. divide into two lists
2. reverse 2nd list
3. merge two lists
*/
public class ReorderList {
	public void reorderList(ListNode head) {
		if (head == null || head.next == null) {
			return;
		}
		ListNode mid = findMid(head);
		ListNode head2 = mid.next;
		mid.next = null;
		head2 = reverse(head2);
		merge(head, head2);
	}

	private ListNode findMid(ListNode head) {
		ListNode slow = head;
		ListNode fast = head.next;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}

	private ListNode reverse(ListNode head) {
		ListNode prev = null;
		while (head != null) {
			ListNode next = head.next;
			head.next = prev;
			prev = head;
			head = next;
		}
		return prev;
	}

	private void merge(ListNode head1, ListNode head2) {
		ListNode dummy = new ListNode(0);
		ListNode cur = dummy;
		while (head1 != null && head2 != null) {
			cur.next = head1;
			head1 = head1.next;
			cur.next.next = head2;
			head2 = head2.next;
			cur = cur.next.next;
		}
		if (head1 != null) {
			cur.next = head1;
		}
	}
}
