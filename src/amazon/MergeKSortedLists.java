package amazon;

import java.util.PriorityQueue;

import utils.ListNode;

/*
 1. use a heap, offer the head of each list into the heap
 2. expand from heap and generate the next element from this node into the heap; connect polled node to result list
 3. Terminate when the heap is empty
 M lists, length if N
 Time: O(M * N * logM)
 Space: O(M)
 */
public class MergeKSortedLists {
	public ListNode mergeKLists(ListNode[] lists) {
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
}
