package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time.
Each transformed word must exist in the word list.
Note:
Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.

Example 1:
Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]
Output: 5

Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.
 */
/*
1. make a wordMap: <word, list of neighbors>
    a. put all words in a set
    b. for each word including the start word, change one char, see if it's in the set. If yes, put in wordMap.
2. use a hashset to store words we've seen
3. from start, do bfs, for each new word, put in distMap, keep track of step. When endWord is found, return step.
4. terminate: end word is found or bfs is done but end word not found

wordList length: M; length of each word: N; neighbor counts: K
Time: M * N (build wordMap) + (M + K) (bfs)
Space: M * N
*/
public class WordLadder {
	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		if (wordList == null || wordList.size() == 0)
			return 0;

		Map<String, List<String>> wordMap = buildWordMap(beginWord, wordList);
		Set<String> seen = new HashSet<>();
		Queue<String> queue = new ArrayDeque<>();
		queue.offer(beginWord);
		int step = 1;
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				String word = queue.poll();
				if (word.equals(endWord))
					return step;

				List<String> neighbors = wordMap.get(word);
				for (String nei : neighbors) {
					if (seen.add(nei)) {
						queue.offer(nei);
					}
				}
			}
			step++;
		}
		return 0;
	}

	private Map<String, List<String>> buildWordMap(String beginWord, List<String> wordList) {
		Set<String> words = new HashSet<>();
		for (String word : wordList)
			words.add(word);

		words.add(beginWord);

		Map<String, List<String>> wordMap = new HashMap<>();
		for (String word : words) {
			wordMap.put(word, new ArrayList<>());
			for (int i = 0; i < word.length(); i++) {
				for (char c = 'a'; c <= 'z'; c++) {
					if (c == word.charAt(i))
						continue;

					char[] array = word.toCharArray();
					array[i] = c;
					String newWord = new String(array);
					if (words.contains(newWord))
						wordMap.get(word).add(newWord);
				}
			}
		}
		return wordMap;
	}

	/*
	Do bi-directional BFS from start and end at the same time
	 */
	public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
		if (wordList == null || wordList.size() == 0)
			return 0;

		Map<String, List<String>> wordMap = buildWordMap(beginWord, wordList);
		if (!wordMap.containsKey(endWord)) {
			return 0;
		}
		Map<String, Integer> seen1 = new HashMap<>();
		seen1.put(beginWord, 1);
		Map<String, Integer> seen2 = new HashMap<>();
		seen2.put(endWord, 0);

		Queue<String> queue1 = new ArrayDeque<>();
		Queue<String> queue2 = new ArrayDeque<>();
		queue1.offer(beginWord);
		queue2.offer(endWord);

		while (!queue1.isEmpty() && !queue2.isEmpty()) {
			String word1 = queue1.poll();
			int step1 = seen1.get(word1);
			String word2 = queue2.poll();
			int step2 = seen2.get(word2);
			if (seen1.containsKey(word2)) {
				return seen1.get(word2) + step2;
			}
			for (String nei : wordMap.get(word1)) {
				if (seen2.containsKey(nei)) {
					return seen2.get(nei) + step1 + 1;
				}
				if (!seen1.containsKey(nei)) {
					seen1.put(nei, step1 + 1);
					queue1.offer(nei);
				}
			}
			for (String nei : wordMap.get(word2)) {
				if (seen1.containsKey(nei)) {
					return seen1.get(nei) + step2 + 1;
				}
				if (!seen2.containsKey(nei)) {
					seen2.put(nei, step2 + 1);
					queue2.offer(nei);
				}
			}
		}

		return 0;
	}
}
