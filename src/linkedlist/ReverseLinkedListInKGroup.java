package linkedlist;

import utils.ListNode;

public class ReverseLinkedListInKGroup {
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
