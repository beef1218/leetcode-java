package other;
/*
Design a Tic-tac-toe game that is played between two players on a n x n grid.

You may assume the following rules:
A move is guaranteed to be valid and is placed on an empty block.
Once a winning condition is reached, no more moves is allowed.
A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
 */
public class TicTacToe {
	private int[][] board;
	private int size;
	public TicTacToe(int n) {
		size = n;
		board = new int[n][n];
	}

	/** Player {player} makes a move at ({row}, {col}).
	 @param row The row of the board.
	 @param col The column of the board.
	 @param player The player, can be either 1 or 2.
	 @return The current winning condition, can be either:
	 0: No one wins.
	 1: Player 1 wins.
	 2: Player 2 wins. */
	public int move(int row, int col, int player) {
		if (row < 0 || row >= size || col < 0 || col >= size ||
				player != 1 && player != 2 || board[row][col] != 0) {
			throw new IllegalArgumentException("player enter valid input");
		}
		board[row][col] = player;
		if (checkWinner(row, col, player)) {
			return player;
		} else {
			return 0;
		}
	}

	private boolean checkWinner(int row, int col, int player) {
		if (checkRow(row, player) || checkCol(col, player) || checkDiagnal(row, col, player)) {
			return true;
		}
		return false;
	}

	private boolean checkRow(int row, int player) {
		for (int i = 0; i < size; i++) {
			if (board[row][i] != player) {
				return false;
			}
		}
		return true;
	}

	private boolean checkCol(int col, int player) {
		for (int i = 0; i < size; i++) {
			if (board[i][col] != player) {
				return false;
			}
		}
		return true;
	}

	private boolean checkDiagnal(int row, int col, int player) {
		boolean won = true;
		for (int i = 0; i < size; i++) {
			if (board[i][i] != player) {
				won = false;
				break;
			}
		}
		if (won) {
			return true;
		}
		won = true;
		for (int i = 0; i < size; i++) {
			if (board[i][size - i - 1] != player) {
				won = false;
				break;
			}
		}
		return won;
	}
}
