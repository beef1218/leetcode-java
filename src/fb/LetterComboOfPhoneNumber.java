package fb;

import java.util.ArrayList;
import java.util.List;

/*
Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

Example:
Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 */
/*
                root(23)
        a            b            c
    ad  ae  af
DFS:
for 0 and 1, no letter is available, so skip to the next level of the tree
Time: O(4 ^ n)
Space: O(n)
*/
public class LetterComboOfPhoneNumber {
	final static String[] PHONE_PAD = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

	public List<String> letterCombinations(String digits) {
		List<String> result = new ArrayList<>();
		if (digits == null || digits.length() == 0) {
			return result;
		}
		helper(0, digits, new StringBuilder(), result);
		return result;
	}

	private void helper(int index, String digits, StringBuilder sb, List<String> result) {
		if (index == digits.length()) {
			result.add(sb.toString());
			return;
		}
		String letters = PHONE_PAD[digits.charAt(index) - '0'];
		if (letters.length() == 0) {
			helper(index + 1, digits, sb, result);
		} else {
			for (char c : letters.toCharArray()) {
				sb.append(c);
				helper(index + 1, digits, sb, result);
				sb.deleteCharAt(sb.length() - 1);
			}
		}
	}
}
