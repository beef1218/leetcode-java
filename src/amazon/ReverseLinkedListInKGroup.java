package amazon;

import utils.ListNode;

/*
Iterative:
 1. use a dummy head to connect head
 2. count k nodes, reverse (pass in prevTail and head of next part); update prevTail to newTail
 3. when reverse: (prevTail and nextHead were passed in)
    reverse from [prevTail.next, nextHead)
    prevTail.next = newHead; tail.next = head of remaining; return newTail
 */
public class ReverseLinkedListInKGroup {
	public ListNode reverseKGroupIterative(ListNode head, int k) {
		if (head == null)
			return head;

		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode prev = dummy;
		int count = 0;
		while (head != null) {
			head = head.next;
			count++;
			if (count % k == 0) {
				prev = reverse(prev, head);
			}
		}
		return dummy.next;
	}

	private ListNode reverse(ListNode prevTail, ListNode nextHead) {
		ListNode head = prevTail.next;
		ListNode cur = head;
		ListNode prev = nextHead;
		while (cur != nextHead) {
			ListNode next = cur.next;
			cur.next = prev;
			prev = cur;
			cur = next;
		}
		prevTail.next = prev;
		return head;
	}

	public ListNode reverseKGroupRecursion(ListNode head, int k) {
		if (head == null)
			return head;

		ListNode cur = head;
		for (int i = 0; i < k; i++) {
			// if there are not k nodes left, return head
			if (cur == null)
				return head;

			cur = cur.next;
		}
		ListNode nextHead = reverseKGroupRecursion(cur, k);
		ListNode newHead = reverse(head, k);
		head.next = nextHead;
		return newHead;
	}

	private ListNode reverse(ListNode head, int k) {
		ListNode cur = head;
		ListNode prev = null;
		for (int i = 0; i < k; i++) {
			ListNode next = cur.next;
			cur.next = prev;
			prev = cur;
			cur = next;
		}
		return prev;
	}
}
