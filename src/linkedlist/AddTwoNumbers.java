package linkedlist;

import utils.ListNode;

/*
You are given two linked lists representing two non-negative numbers.
The digits are stored in reverse order and each of their nodes contain a single digit.
Add the two numbers and return it as a linked list.

Example

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)

Output: 7 -> 0 -> 8
 */
public class AddTwoNumbers {
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		if (l1 == null)
			return l2;

		if (l2 == null)
			return l1;

		ListNode dummy = new ListNode(0);
		ListNode cur = dummy;
		int carry = 0;
		while (l1 != null || l2 != null || carry > 0) {
			int num1 = l1 == null ? 0 : l1.value;
			int num2 = l2 == null ? 0 : l2.value;
			int sum = num1 + num2 + carry;
			carry = sum / 10;
			sum %= 10;
			cur.next = new ListNode(sum);
			if (l1 != null)
				l1 = l1.next;

			if (l2 != null)
				l2 = l2.next;

			cur = cur.next;
		}
		return dummy.next;
	}
}
