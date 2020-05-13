package string;

/*
Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.

Example:
Given "bcabc"
Return "abc"

Given "cbacdcbc"
Return "acdb"
*/
/*
1. build count array to store the count of each char
2. use a stringbuilder to store result; use a boolean array to track used chars
3. linear scan, for each char cur:
      case1: if cur is not used: 
                      while stack top >= cur and there are more of top char to come later, pop; push cur into stack; decrease count array
      case2: if cur is used: decrease count array

return stringbuilder
Time: O(n)
Space: O(n)
*/
public class RemoveDupLetterLexicographical {
	public String removeDuplicateLetters(String input) {
		if (input == null || input.length() == 0)
			return "";

		int[] count = new int[26];
		for (char s : input.toCharArray()) {
			count[s - 'a']++;
		}
		StringBuilder sb = new StringBuilder();
		boolean[] used = new boolean[26];
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (!used[c - 'a']) {
				while (sb.length() > 0 && sb.charAt(sb.length() - 1) >= c
						&& count[sb.charAt(sb.length() - 1) - 'a'] > 0) {
					used[sb.charAt(sb.length() - 1) - 'a'] = false;
					sb.deleteCharAt(sb.length() - 1);
				}
				sb.append(c);
				used[c - 'a'] = true;
			}
			count[c - 'a']--;
		}
		return sb.toString();
	}
}
