package other;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
/*
1. use an array[26] to store count of each task
2. add each count into maxHeap
3. pull from maxHeap, decrease, if still > 0, put into a list. Repeat n times, then offer list back to maxHeap
4. keep tracking intervals

Time: O(nlogn)
Space: O(n)
*/
public class TaskScheduler {
	public int leastInterval(char[] tasks, int n) {
		if (tasks == null || tasks.length == 0)
			return 0;

		int[] array = new int[26];
		for (char c : tasks) {
			array[c - 'A']++;
		}

		PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> a < b ? b : a);
		for (int count : array) {
			if (count > 0)
				maxHeap.offer(count);
		}

		int interval = 0;
		while (!maxHeap.isEmpty()) {
			List<Integer> wait = new ArrayList<>();
			for (int i = 0; i <= n; i++) {
				if (maxHeap.isEmpty() && wait.size() == 0)
					return interval;

				interval++;
				if (!maxHeap.isEmpty()) {
					int count = maxHeap.poll();
					count--;
					if (count > 0)
						wait.add(count);
				}
			}
			for (int num : wait)
				maxHeap.offer(num);
		}
		return interval;
	}
}
