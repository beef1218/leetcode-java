package fb;
/*
Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
 */
/*
Recursion, carry a boolean to indicate whether delete chance has been used.
Time: n
Space: n
 */
public class ValidPalindrome2 {
	public static void main(String[] args) {
		ValidPalindrome2 solution = new ValidPalindrome2();
		String input = "eeccccbebaeeabebccceea";
		System.out.println(solution.validPalindrome(input));
	}
	public boolean validPalindrome(String s) {
		if (s == null || s.length() <= 1) {
			return true;
		}
		return helper(s, 0, s.length() - 1, true);
	}
	private boolean helper(String s, int left, int right, boolean canDelete) {
		if (left >= right) {
			return true;
		}
		if (s.charAt(left) == s.charAt(right)) {
			return helper(s, left + 1, right - 1, canDelete);
		} else if (!canDelete) {
			return false;
		} else {
			return helper(s, left + 1, right, false) || helper(s, left, right - 1, false);
		}
	}
}
