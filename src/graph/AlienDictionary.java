package graph;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
/*
There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you.
You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language.
Derive the order of letters in this language.

Example 1:
Given the following words in dictionary,

[
"wrt",
"wrf",
"er",
"ett",
"rftt"
]
The correct order is: "wertf".

1a. build an inDegree array: index char from 'a' - 'z'; value: number of chars before it
1b. build an outDegree map: <char, set of chars after it>. It needs to contain all char appeared
topological sort:
2. for each char in outDegree, if its inDegree value is zero, put in queue and result
3. poll from queue, get depending chars from outDegree, decrease their inDegree value. When it reaches zero, add to queue
Terminate when queue is empty
4. if result size == map.size(), valid result is found; else, no valid result exists

M number of words. Longest word has length of N
Time: M * N + number of dependency (E + V)
Space: M * N
*/
public class AlienDictionary {
	public static void main(String[] args) {
		AlienDictionary solution = new AlienDictionary();
		String[] input = new String[]{"za","zb","ca","cb"};
		String result = solution.alienOrder(input);
		System.out.println(result);
	}
	public String alienOrder(String[] words) {
		if (words == null || words.length == 0)
			return "";

		int[] inDegree = new int[26];
		Map<Character, Set<Character>> outDegree = new HashMap<>();
		processWords(words, inDegree, outDegree);
		String result = getOrder(inDegree, outDegree);
		if (result.length() != outDegree.size())
			return "";

		return result;
	}
	private void processWords(String[] words, int[] inDegree, Map<Character, Set<Character>> outDegree) {
		for (String word : words) {
			for (char c : word.toCharArray()) {
				if (!outDegree.containsKey(c))
					outDegree.put(c, new HashSet<>());
			}
		}
		for (int i = 0; i < words.length - 1; i++) {
			String word1 = words[i];
			String word2 = words[i + 1];
			if (word1.equals(word2))
				continue;

			for (int j = 0; j < word1.length() && j < word2.length(); j++) {
				char c1 = word1.charAt(j);
				char c2 = word2.charAt(j);
				if (c1 != c2) {
					Set<Character> set = outDegree.get(c1);
					if (set.add(c2))
						inDegree[c2 -'a']++;

					break; // only check the first different char
				}
			}
		}
	}
	private String getOrder(int[] inDegree, Map<Character, Set<Character>> outDegree) {
		StringBuilder sb = new StringBuilder();
		Queue<Character> queue = new ArrayDeque<>();
		// add chars without dependency into queue
		for (char c : outDegree.keySet()) {
			if (inDegree[c - 'a'] == 0)
				queue.offer(c);
		}
		while (!queue.isEmpty()) {
			char c = queue.poll();
			sb.append(c);
			for (char c2 : outDegree.get(c)) {
				inDegree[c2 - 'a']--;
				if (inDegree[c2 - 'a'] == 0) {
					queue.offer(c2);
				}
			}
		}
		return sb.toString();
	}
}
