package other;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/*Design a search autocomplete system for a search engine. Users may input a sentence (at least one word and end with a special character '#'). For each character they type except '#', you need to return the top 3 historical hot sentences that have prefix the same as the part of sentence already typed. Here are the specific rules:

The hot degree for a sentence is defined as the number of times a user typed the exactly same sentence before.
The returned top 3 hot sentences should be sorted by hot degree (The first is the hottest one). If several sentences have the same degree of hot, you need to use ASCII-code order (smaller one appears first).
If less than 3 hot sentences exist, then just return as many as you can.
When the input is a special character, it means the sentence ends, and in this case, you need to return an empty list.
Your job is to implement the following functions:

The constructor function:

AutocompleteSystem(String[] sentences, int[] times): This is the constructor. The input is historical data. Sentences is a string array consists of previously typed sentences. Times is the corresponding times a sentence has been typed. Your system should record these historical data.

Now, the user wants to input a new sentence. The following function will provide the next character the user types:

List<String> input(char c): The input c is the next character typed by the user. The character will only be lower-case letters ('a' to 'z'), blank space (' ') or a special character ('#'). Also, the previously typed sentence should be recorded in your system. The output will be the top 3 historical hot sentences that have prefix the same as the part of sentence already typed.


Example:
Operation: AutocompleteSystem(["i love you", "island","ironman", "i love leetcode"], [5,3,2,2])
The system have already tracked down the following sentences and their corresponding times:
"i love you" : 5 times
"island" : 3 times
"ironman" : 2 times
"i love leetcode" : 2 times
Now, the user begins another search:

Operation: input('i')
Output: ["i love you", "island","i love leetcode"]
Explanation:
There are four sentences that have prefix "i". Among them, "ironman" and "i love leetcode" have same hot degree. Since ' ' has ASCII code 32 and 'r' has ASCII code 114, "i love leetcode" should be in front of "ironman". Also we only need to output top 3 hot sentences, so "ironman" will be ignored.

Operation: input(' ')
Output: ["i love you","i love leetcode"]
Explanation:
There are only two sentences that have prefix "i ".

Operation: input('a')
Output: []
Explanation:
There are no sentences that have prefix "i a".

Operation: input('#')
Output: []
Explanation:
The user finished the input, the sentence "i a" should be saved as a historical sentence in system. And the following input will be counted as a new search.
 */

/*
Use Trie. This still has bugs
 */
public class AutocompleteSystem {
	static class TrieNode {
		int count = 0;
		String sentence;
		TrieNode[] children;
		TrieNode() {
			children = new TrieNode[27];
		}
	}
	private TrieNode root;
	private TrieNode cur = root;
	private StringBuilder sb;

	public AutocompleteSystem(String[] sentences, int[] times) {
		root = new TrieNode();
		buildTrie(sentences, times);
		sb = new StringBuilder();
	}

	private void buildTrie(String[] sentences, int[] times) {
		for (int i = 0; i < sentences.length; i++) {
			String sentence = sentences[i];
			TrieNode node = root;
			for (int j = 0; j < sentence.length(); j++) {
				char c = sentence.charAt(j);
				c = Character.toLowerCase(c);
				int index = c == ' ' ? 26 : c - 'a';
				if (node.children[index] == null) {
					node.children[index] = new TrieNode();
				}
				node = node.children[index];
				if (j == sentence.length() - 1) {
					node.sentence = sentence;
					node.count += times[i];
				}
			}
		}
	}

	public List<String> input(char c) {
		if (c == '#') {
			addSentence();
			sb = new StringBuilder();
			cur = root;
			return new ArrayList<>();
		} else {
			sb.append(c);
			return getSentences(c);
		}
	}

	private void addSentence() {
		cur = root;
		for (int i = 0; i < sb.length(); i++) {
			char c = sb.charAt(i);
			c = Character.toLowerCase(c);
			int index = c == ' ' ? 26 : c - 'a';
			if (cur.children[index] == null) {
				cur.children[index] = new TrieNode();
			}
			cur = cur.children[index];
			if (i == sb.length() - 1) {
				cur.sentence = sb.toString();
				cur.count++;
			}
		}
	}

	private List<String> getSentences(char c) {
		List<String> result = new ArrayList<>();
		List<TrieNode> nodes = new ArrayList<>();
		int index = c == ' ' ? 26 : c - 'a';
		if (cur == null || cur.children[index] == null) {
			cur = null;
			return result;
		}
		cur = cur.children[index];
		getSentences(cur, nodes);
		Collections.sort(nodes, (a, b) -> {
			if (a.count != b.count) {
				return a.count < b.count ? 1 : -1;
			}
			return a.sentence.compareTo(b.sentence);
		});
		for (int i = 0; i < 3; i++) {
			result.add(nodes.get(i).sentence);
		}
		return result;
	}

	private void getSentences(TrieNode node, List<TrieNode> result) {
		if (node == null) {
			return;
		}
		if (node.sentence != null) {
			result.add(node);
		}
		for (int i = 0; i <= 26; i++) {
			getSentences(node.children[i], result);
		}
	}
}
