package dfs;

public class FlipGame22 {
	public static void main(String[] args) {
		FlipGame22 solution = new FlipGame22();
		String input = "+++-++";
		boolean result = solution.canWin2(input);
		System.out.println(result);
	}
	public boolean canWin2(String input) {
		if (input == null || input.length() < 2)
			return false;

		return helper(input.toCharArray());
	}
	private boolean helper(char[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			if (array[i] == '+' && array[i + 1] == '+') {
				array[i] = '-';
				array[i + 1] = '-';
				if (!helper(array))
					return true;
				array[i] = '+';
				array[i + 1] = '+';
			}
		}
		return false;
	}

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
