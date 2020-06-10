package linkedlist;

import utils.ListNode;

/*
Given a singly linked list, determine if it is a palindrome.
 */
public class PalindromeLinkedList {
	/*
	Method 1:
	1. find mid
	2. reverse 2nd half of the list in place
	3. use two pointers to check
	Time: O(n)
	Space: O(1)
	*/
	public boolean isPalindrome1(ListNode head) {
		if (head == null || head.next == null) {
			return true;
		}
		ListNode mid = findMid(head);
		ListNode head2 = mid.next;
		head2 = reverseList(head2);
		while (head2 != null) {
			if (head2.val != head.val) {
				return false;
			}
			head2 = head2.next;
			head = head.next;
		}
		return true;
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

	private ListNode reverseList(ListNode head) {
		ListNode prev = null;
		while (head != null) {
			ListNode next = head.next;
			head.next = prev;
			prev = head;
			head = next;
		}
		return prev;
	}

	/*
	Method 2: (recursive)
	1. Use an instance variable to store the original head
	2. Do recursion to reach the tail
	3. On each back-tracking (a call stack returned to previous call stack), compare the tail with the original head
	4. On each call stack, move the original head
	 */
	ListNode originalHead;
	public boolean isPalindrome(ListNode head) {
		if (head == null || head.next == null) {
			return true;
		}
		originalHead = head;
		return helper(head.next);
	}
	private boolean helper(ListNode node) {
		if (node == null || node == originalHead) {
			return true;
		}
		if (!helper(node.next) || node.val != originalHead.val) {
			return false;
		}
		originalHead = originalHead.next;
		return true;
	}
}
