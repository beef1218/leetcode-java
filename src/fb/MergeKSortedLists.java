package fb;

import utils.ListNode;

import java.util.PriorityQueue;

public class MergeKSortedLists {
	/*
	Method1: k-way merge with heap
	 1. use a heap, offer the head of each list into the heap
	 2. expand from heap and generate the next element from this node into the heap; connect polled node to result list
	 3. Terminate when the heap is empty
	 M lists, length is N
	 Time: O(M * N * logM)
	 Space: O(M)
	 */
	public ListNode mergeKLists1(ListNode[] lists) {
		if (lists == null || lists.length == 0) {
			return null;
		}
		PriorityQueue<ListNode> heap = new PriorityQueue<>(lists.length, (a, b) -> Integer.valueOf(a.val).compareTo(b.val));
		for (ListNode node : lists) {
			if (node != null) {
				heap.offer(node);
			}
		}
		ListNode dummy = new ListNode(0);
		ListNode cur = dummy;
		while (!heap.isEmpty()) {
			cur.next = heap.poll();
			cur = cur.next;
			if (cur.next != null) {
				heap.offer(cur.next);
			}
		}
		return dummy.next;
	}
	/*
	Method2: binary reduction
	M lists, length is N
	Time: O(M * N * logM)
	Space: O(1)
	 */
	public ListNode mergeKLists2(ListNode[] lists) {
		if (lists == null || lists.length == 0) {
			return null;
		}
		int interval = 1;
		int size = lists.length;
		while (interval < size) {
			for (int i = 0; i + interval < size; i += interval * 2) {
				lists[i] = merge(lists[i], lists[i + interval]);
			}
			interval *= 2;
		}
		return lists[0];
	}
	private ListNode merge(ListNode head1, ListNode head2) {
		ListNode dummy = new ListNode(0);
		ListNode cur = dummy;
		while (head1 != null && head2 != null) {
			if (head1.val < head2.val) {
				cur.next = head1;
				head1 = head1.next;
			} else {
				cur.next = head2;
				head2 = head2.next;
			}
			cur = cur.next;
		}
		if (head1 != null) {
			cur.next = head1;
		} else if (head2 != null) {
			cur.next = head2;
		}
		return dummy.next;
	}
}
