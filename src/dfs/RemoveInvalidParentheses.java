package dfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
Note: The input string may contain letters other than the parentheses ( and ).

Example 1:
Input: "()())()"
Output: ["()()()", "(())()"]

Example 2:
Input: "(a)())()"
Output: ["(a)()()", "(a())()"]

Example 3:
Input: ")("
Output: [""]
 */
/*
1. do linear scan, find out number of left and right to be removed
2. do dfs, for every char, remove / not remove
3. use a set to store results for dedup

at each node:
1. not delete
2.1 delete a left
2.2 delete a right


                dfs(())))
        remove 1st             not remove 1st
    remove 2nd  not remove 2nd

Time: 2 ^ n
Space: n
*/
public class RemoveInvalidParentheses {
	public List<String> removeInvalidParentheses(String s) {
		if (s == null) {
			return new ArrayList<>();
		}
		int[] toRemove = countToBeRemove(s);
		Set<String> resultSet = new HashSet<>();
		helper(0, toRemove[0], toRemove[1], 0, 0, s, new StringBuilder(), resultSet);
		return new ArrayList<>(resultSet);
	}
	private void helper(int index, int leftToDelete, int rightToDelete, int leftCount, int rightCount, String s, StringBuilder sb, Set<String> result) {
		if (index == s.length()) {
			if (leftToDelete == 0 && rightToDelete == 0) {
				result.add(sb.toString());
			}
			return;
		}
		char c = s.charAt(index);
		// not remove current char
		sb.append(c);
		if (c == '(') {
			helper(index + 1, leftToDelete, rightToDelete, leftCount + 1, rightCount, s, sb, result);
		} else if (c == ')') {
			if (leftCount > rightCount) {
				helper(index + 1, leftToDelete, rightToDelete, leftCount, rightCount + 1, s, sb,                              result);
			}
		} else {
			helper(index + 1, leftToDelete, rightToDelete, leftCount, rightCount, s, sb, result);
		}
		sb.deleteCharAt(sb.length() - 1);

		// remove left
		if (c == '(' && leftToDelete > 0) {
			helper(index + 1, leftToDelete - 1, rightToDelete, leftCount, rightCount, s, sb, result);
		} else if (c == ')' && rightToDelete > 0) { // remove right
			helper(index + 1, leftToDelete, rightToDelete - 1, leftCount, rightCount, s, sb, result);
		}
	}
	private int[] countToBeRemove(String s) {
		int right = 0;
		int count = 0;
		for (char c : s.toCharArray()) {
			if (c != '(' && c != ')') {
				continue;
			}
			if (c == '(') {
				count++;
			} else if (count > 0) {
				count--;
			} else {
				right++;
			}
		}
		return new int[]{count, right};
	}

}
