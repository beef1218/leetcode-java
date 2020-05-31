package heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/*
1. build a map <word : count>
2. put entry into priorityqueue, specify own comparator
*/
public class TopKFrequentWords {
	public List<String> topKFrequent(String[] words, int k) {
		List<String> result = new ArrayList<>();
		if (words == null || words.length == 0 || k <= 0)
			return result;

		Map<String, Integer> wordMap = new HashMap<>();
		for (String word : words) {
			wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
		}
		k = Math.min(k, wordMap.size()); // important, to avoid exception
		PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(k + 1, (a, b) -> !a.getValue().equals(b.getValue()) ? a.getValue().compareTo(b.getValue()) : b.getKey().compareTo(a.getKey()));

		for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {
			pq.offer(entry);
			if (pq.size() > k)
				pq.poll();
		}
		while (!pq.isEmpty())
			result.add(pq.poll().getKey());

		Collections.reverse(result);
		return result;
	}
}
