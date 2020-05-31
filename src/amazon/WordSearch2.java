package amazon;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
Given a 2D board and a list of words from the dictionary, find all words in the board.
Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring.
The same letter cell may not be used more than once in a word.

Example:
Input:
board = [
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
words = ["oath","pea","eat","rain"]
Output: ["eat","oath"]

Note:
All inputs are consist of lowercase letters a-z.
The values of words are distinct.
 */
/*
1. build a trie with words
2. for each char in board, do dfs in trie
dfs:
    base case: out of bound / children node not exist / isWord (add to result)
    recursive rule: for each neighbor, do dfs

M * N matrix; length of words is L; size of dictionary is K
Time: K * L + M * N * 4 ^ L
Space: K * L + M * N
*/
public class WordSearch2 {
	static class TrieNode {
		boolean isWord;
		TrieNode[] children;
		TrieNode (boolean isWord) {
			this.isWord = isWord;
			children = new TrieNode[26];
		}
	}
	final static int[][] DIRS = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	public List<String> findWords(char[][] board, String[] words) {
		Set<String> result = new HashSet<>();
		if (board == null || board.length == 0 || board[0].length == 0 || words == null || words.length == 0) {
			return new ArrayList<>();
		}
		TrieNode root = buildTree(words); // build a trie tree
		boolean[][] used = new boolean[board.length][board[0].length];
		StringBuilder sb = new StringBuilder();
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[0].length; c++) {
				dfs(r, c, board, used, root, result, sb);
			}
		}
		return new ArrayList<>(result);
	}
	private void dfs(int r, int c, char[][] board, boolean[][] used, TrieNode node, Set<String> result, StringBuilder sb) {
		final int row = board.length;
		final int col = board[0].length;
		if (r < 0 || r >= row || c < 0 || c >= col || used[r][c] || node.children[board[r][c] - 'a'] == null) {
			return;
		}
		node = node.children[board[r][c] - 'a'];
		sb.append(board[r][c]);
		used[r][c] = true;
		if (node.isWord) {
			result.add(sb.toString());
		}
		for (int[] dir : DIRS) {
			dfs(r + dir[0], c + dir[1], board, used, node, result, sb);
		}
		used[r][c] = false;
		sb.deleteCharAt(sb.length() - 1);
	}
	private TrieNode buildTree(String[] words) {
		TrieNode root = new TrieNode(false);
		for (String word : words) {
			TrieNode cur = root;
			for (int i = 0; i < word.length(); i++) {
				char c = word.charAt(i);
				if (cur.children[c - 'a'] == null) {
					cur.children[c - 'a'] = new TrieNode(false);
				}
				cur = cur.children[c - 'a'];
				if (i == word.length() - 1) {
					cur.isWord = true;
				}
			}
		}
		return root;
	}
}
