package amazon;

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
    b. for each word including the start word, change one char, see if it's in the set. If yes, put in  wordMap.
2. use a hashset to store words we've seen
3. from start, do bfs, for each new word, put in distMap. When endWord is found, return dist.
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
}
