package linkedlist;

import java.util.List;
import java.util.PriorityQueue;

import utils.ListNode;
/*
1. use minHeap
2. put the first element of each list into maxHeap
3. poll from maxHeap and connect elements.
 */
public class MergeKSortedLists {
	public ListNode merge(List<ListNode> listOfLists) {
		if (listOfLists == null || listOfLists.size() == 0)
			return null;

		ListNode dummy = new ListNode(0);
		ListNode cur = dummy;
		PriorityQueue<ListNode> minHeap = new PriorityQueue<>(listOfLists.size(), (a,b) -> a.value - b.value);
		for (ListNode node : listOfLists) {
			minHeap.offer(node);
		}
		while (!minHeap.isEmpty()) {
			cur.next = minHeap.poll();
			cur = cur.next;
			if (cur.next != null)
				minHeap.offer(cur.next);
		}
		return dummy.next;
	}
}
