package dfs;
/*
You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--".
The game ends when a person can no longer make a move and therefore the other person will be the winner.
Write a function to determine if the starting player can guarantee a win.

For example, given s = "++++", return true. The starting player can guarantee a win by flipping the middle "++" to become "+--+".
 */
/*
dfs: for each possible flip position, flip it, call recursion (see if opponent can win)
if opponent can win, that means I lose, need to continue.
Note: need to make sure to unflip previous flipped place.
 */
public class FlipGame2 {
	public static void main(String[] args) {
		FlipGame2 solution = new FlipGame2();
		String input = "+++-++";
		boolean result = solution.canWin2(input);
		System.out.println(result);
	}
	/*
	better solution, not creating new string at each level. Only stack call space is used
	 */
	public boolean canWin2(String input) {
		if (input == null || input.length() < 2)
			return false;

		return helper(input.toCharArray());
	}
	private boolean helper(char[] array) {
		boolean result = false;
		for (int i = 0; i < array.length - 1; i++) {
			if (array[i] == '+' && array[i + 1] == '+') {
				array[i] = '-';
				array[i + 1] = '-';
				if (!helper(array))
					return true;
					//result = true;

				array[i] = '+';
				array[i + 1] = '+';
			}
		}
		return result;
	}

	/*
	not best solution, create new string at each level.
	 */
	public boolean canWin(String input) {
		if (input == null || input.length() < 2)
			return false;

		for (int i = 0; i < input.length() - 1; i++) {
			if (input.charAt(i) == '+' && input.charAt(i + 1) == '+') {
				String next = input.substring(0, i) + "--" + input.substring(i + 2);
				if (!canWin(next))
					return true;
			}
		}
		return false;
	}
}
