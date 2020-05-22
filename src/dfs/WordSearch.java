package dfs;

/*
Given a 2D board and a word, find if the word exists in the grid.
The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

Example:
board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.
 */
/*
for each char, do dfs (with index)
1. base case: if index == input.length(), return true;
			  if out of bound or used or different char, return false
2. recursive rule: for each neighbor, do dfs

Time: 4 ^ n
Space: n
 */
public class WordSearch {
	static int[][] dirs = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

	public boolean exist(char[][] board, String word) {
		if (board == null || board.length == 0 || board[0].length == 0)
			return false;

		boolean[][] used = new boolean[board.length][board[0].length];
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[0].length; c++) {
				if (helper(board, used, 0, word, r, c))
					return true;
			}
		}
		return false;
	}

	private boolean helper(char[][] board, boolean[][] used, int index, String word, int r, int c) {
		if (index == word.length())
			return true;

		int row = board.length;
		int col = board[0].length;
		if (r < 0 || c < 0 || r >= row || c >= col || used[r][c] || board[r][c] != word.charAt(index))
			return false;

		used[r][c] = true;
		for (int[] dir : dirs) {
			int y = r + dir[0];
			int x = c + dir[1];
			if (helper(board, used, index + 1, word, y, x))
				return true;
		}
		used[r][c] = false;
		return false;
	}
}
