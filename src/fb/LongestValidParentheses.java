package fb;
/*
Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

Example 1:
Input: "(()"
Output: 2
Explanation: The longest valid parentheses substring is "()"

Example 2:
Input: ")()())"
Output: 4
Explanation: The longest valid parentheses substring is "()()"
 */
/*
scan from left to right. globalMax, left, right, count
1. when right is a '(', count++
2. when right is a ')',
    if count == 0, left = right + 1
    else, count--. if count reaches 0, update globalMax with right - left + 1

repeat but from right to left
return globalMax
Time: O(n)
Space: O(1)
*/
public class LongestValidParentheses {
	public int longestValidParentheses(String s) {
		if (s == null || s.length() <= 1) {
			return 0;
		}
		int max = 0;
		// scan from left to right
		int left = 0;
		int right = 0;
		int count = 0;
		while (right < s.length()) {
			char c = s.charAt(right);
			if (c == '(') {
				count++;
			} else {
				if (count == 0) {
					left = right + 1;
				} else {
					count--;
					if (count == 0) {
						max = Math.max(max, right - left + 1);
					}
				}
			}
			right++;
		}
		// scan from right to left
		left = s.length() - 1;
		right = s.length() - 1;
		count = 0;
		while (left >= 0) {
			char c = s.charAt(left);
			if (c == ')') {
				count++;
			} else {
				if (count == 0) {
					right = left - 1;
				} else {
					count--;
					if (count == 0) {
						max = Math.max(max, right - left + 1);
					}
				}
			}
			left--;
		}
		return max;
	}
}
