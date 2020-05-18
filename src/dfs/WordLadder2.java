package dfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
/*
Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:

Only one letter can be changed at a time
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
Note:

Return an empty list if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
Example 1:

Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output:
[
  ["hit","hot","dot","dog","cog"],
  ["hit","hot","lot","log","cog"]
]
 */
/*
1. build neighbor map <word, List of neighbors>
	a. put word list into a hashset
	b. for each word, for each char, replace it with 'a' - 'z'. If it's in the set, put into neighborMap
	c. need to include beginWord as well since it may not be in the word list
2. use a distMap to store steps <word, step from start>
3. use a path map to store predecessors <word, list of predecessors>
4. do bfs, start with beginWord. Poll from queue, get current step from distMap, for each neighbor:
	a. if not in distMap, put in distMap <nei, cur step + 1>; put in pathMap.
	b. if in distMap and distMap.get(nei) == cur step + 1, add to pathMap cuz its another shortest path.
	c. terminate when endWord is poll from queue
5. do dfs with pathMap to find all paths.

 */
public class WordLadder2 {
	public static void main(String[] args) {
		WordLadder2 solution = new WordLadder2();
		List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");
		String beginWord = "hit";
		String endWord = "cog";
		List<List<String>> result = solution.findLadders(beginWord, endWord, wordList);
		System.out.println(result);
	}
	public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
		List<List<String>> result = new ArrayList<>();
		if (wordList == null || wordList.size() == 0)
			return result;

		Map<String, List<String>> wordMap = buildWordMap(beginWord, wordList); // neighbors map
		Map<String, List<String>> pathMap = new HashMap<>(); //predecessor map
		bfs(beginWord, endWord, wordMap, pathMap);
		if (!pathMap.containsKey(endWord))
			return result;

		List<String> cur = new ArrayList<>();
		cur.add(endWord);
		dfs(endWord, cur, pathMap, result);
		return result;
	}
	private void bfs(String beginWord, String endWord, Map<String, List<String>> wordMap, Map<String, List<String>> pathMap) {
		Map<String, Integer> distMap = new HashMap<>(); // distance map
		distMap.put(beginWord, 0);
		pathMap.put(beginWord, null);
		Queue<String> queue = new ArrayDeque<>();
		queue.offer(beginWord);
		while (!queue.isEmpty()) {
			String word = queue.poll();
			if (word.equals(endWord))
				return;

			int step = distMap.get(word);
			List<String> neighbors = wordMap.get(word);
			for (String nei : neighbors) {
				if (!distMap.containsKey(nei)) {
					distMap.put(nei, step + 1);
					List<String> list = new ArrayList<>();
					list.add(word);
					pathMap.put(nei, list);
					queue.offer(nei);
				} else if (step + 1 == distMap.get(nei)) {
					List<String> list = pathMap.get(nei);
					list.add(word);
				}
			}
		}
	}
	private void dfs(String word, List<String> cur, Map<String, List<String>> pathMap, List<List<String>> result) {
		List<String> pres = pathMap.get(word);
		if (pres == null) {
			List<String> path = new ArrayList<>(cur);
			Collections.reverse(path);
			result.add(path);
			return;
		}
		for (String pre : pres) {
			cur.add(pre);
			dfs(pre, cur, pathMap, result);
			cur.remove(cur.size() - 1);
		}
	}
	private Map<String, List<String>> buildWordMap(String beginWord, List<String> wordList) {
		Set<String> words = new HashSet<>(wordList);
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
