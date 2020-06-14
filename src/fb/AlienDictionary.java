package fb;

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

assume no duplicated words. only 26 alphatical letters are used
1. process words
    1.1. inDegree - int[26]: store number of chars that are before current char
    1.2. outDegree - Map<char, list of chars that go after the key>. all chars need to exist in as key
    1.3. compare each pair of words, find the 1st distinct char pairs, add to inDegree and outDegree
2. for each char in outDegree, if its value in inDegree is 0, add to queue
3. poll from queue, add to result, for list of chars of this key in outDegree, decrease their inDegree values. When it reaches zero, add to queue. Terminate when queue is empty.
4. if result list size == outDegree size, we have a valid order.

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
		return result.length() == outDegree.size() ? result : "";
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
