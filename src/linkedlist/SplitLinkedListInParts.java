package linkedlist;
/*
Given a (singly) linked list with head node root, write a function to split the linked list into k consecutive linked list "parts".

The length of each part should be as equal as possible: no two parts should have a size differing by more than 1. This may lead to some parts being null.

The parts should be in order of occurrence in the input list, and parts occurring earlier should always have a size greater than or equal parts occurring later.

Return a List of ListNode's representing the linked list parts that are formed.

Examples 1->2->3->4, k = 5 // 5 equal parts [ [1], [2], [3], [4], null ]
 */
/*
 1. get the length of linkedlist
 2. length / k:
    if 0: each part will be length of 1 or 0
    if > 0: length / k = number of parts
            length % k = first number of parts that has one extra node

Time: O(n)
Space: O(1)
 */

import utils.ListNode;

public class SplitLinkedListInParts {
	public ListNode[] splitListToParts(ListNode root, int k) {
		if (root == null)
			return new ListNode[k];

		int length = getLength(root);
		int partLength = length / k; // if k > length, partLength == 0, the logic still works
		int biggerParts = length % k;
		ListNode[] result = new ListNode[k];
		for (int i = 0; i < k; i++) {
			if (root == null)
				break;

			result[i] = root;
			for (int j = 0; j < partLength + (i < biggerParts ? 1 : 0) - 1; j++) {
				root = root.next;
			}
			ListNode prev = root;
			root = root.next;
			prev.next = null;
		}
		return result;
	}
	private int getLength(ListNode root) {
		int length = 0;
		while (root != null) {
			root = root.next;
			length++;
		}
		return length;
	}
}
