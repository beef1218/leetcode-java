package amazon;

import java.util.ArrayList;
import java.util.List;

/*
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
 */
/*
number of level: 2 * n
each level: choose to use a left or a right on this index
condition: remain of right >= remain of left
        root
      L       R
   L    R   L   R

time: 2 ^ (2n)
space: n
*/
public class ValidParentheses {
	public List<String> generateParenthesis(int n) {
		List<String> result = new ArrayList<>();
		if (n <= 0) {
			return result;
		}
		dfs(n, n, new StringBuilder(), result);
		return result;
	}

	private void dfs(int left, int right, StringBuilder sb, List<String> result) {
		if (left == 0 && right == 0) {
			result.add(sb.toString());
			return;
		}
		if (left > 0) {
			sb.append('(');
			dfs(left - 1, right, sb, result);
			sb.deleteCharAt(sb.length() - 1);
		}
		if (right > left) {
			sb.append(')');
			dfs(left, right - 1, sb, result);
			sb.deleteCharAt(sb.length() - 1);
		}
	}
}
