package trie;

/*
Design a data structure that supports the following two operations:

void addWord(word)
bool search(word)
search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.

Example:
addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true
Note:
You may assume that all words are consist of lowercase letters a-z.
 */
public class WordDictionary {
	static class TrieNode {
		TrieNode[] children = new TrieNode[26];
		boolean isWord = false;
	}

	private TrieNode root;

	public WordDictionary() {
		root = new TrieNode();
	}

	public void addWord(String word) {
		addWordToTrie(word);
	}

	/**
	 * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
	 */
	public boolean search(String word) {
		return search(word, 0, root);
	}

	private boolean search(String word, int index, TrieNode node) {
		if (node == null) {
			return false;
		}
		if (index == word.length()) {
			return node.isWord;
		}
		char c = word.charAt(index);
		if (c != '.') {
			return search(word, index + 1, node.children[c - 'a']);
		} else {
			for (TrieNode child : node.children) {
				if (search(word, index + 1, child)) {
					return true;
				}
			}
			return false;
		}
	}

	private void addWordToTrie(String word) {
		TrieNode cur = root;
		for (char c : word.toCharArray()) {
			int index = c - 'a';
			if (cur.children[index] == null) {
				cur.children[index] = new TrieNode();
			}
			cur = cur.children[index];
		}
		cur.isWord = true;
	}
}
