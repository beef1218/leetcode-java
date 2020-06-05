package other;

import java.util.Collections;
import java.util.PriorityQueue;
/*
1. use a maxHeap and a minHeap. all elements in minHeap are >= then elements in maxHeap
2. For each new element, if new element <= maxHeap.peek(), put in maxHeap; else, put in minHeap.
3. Perform balance to make sure at anytime, size of maxHeap is equal or greater than size of minHeap by 1
*/
public class MedianFromDataStream {
	private PriorityQueue<Integer> minHeap;
	private PriorityQueue<Integer> maxHeap;

	public MedianFromDataStream() {
		minHeap = new PriorityQueue<>();
		maxHeap = new PriorityQueue<>(Collections.reverseOrder());
	}

	public void addNum(int num) {
		if (maxHeap.isEmpty()) {
			maxHeap.offer(num);
			return;
		}
		if (num <= maxHeap.peek()) {
			maxHeap.offer(num);
		} else {
			minHeap.offer(num);
		}
		balance();
	}

	public double findMedian() {
		int size = maxHeap.size() + minHeap.size();
		if (size == 0) {
			return 0.0;
		}
		if (size % 2 == 0) {
			return (maxHeap.peek() + minHeap.peek()) / 2.0;
		} else {
			return (double) maxHeap.peek();
		}
	}

	private void balance() {
		int minSize = minHeap.size();
		int maxSize = maxHeap.size();
		if (maxSize > minSize + 1) {
			minHeap.offer(maxHeap.poll());
		} else if (maxSize < minSize) {
			maxHeap.offer(minHeap.poll());
		}
	}
}
