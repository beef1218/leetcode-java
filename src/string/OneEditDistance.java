package string;

/*
Given two strings s and t, determine if they are both one edit distance apart.

Note:

There are 3 possiblities to satisify one edit distance apart:
Insert a character into s to get t
Delete a character from s to get t
Replace a character of s to get t
 */
/*
recursion(index starts with 0)
if (s at 0 == t at 0): return recursionI(index + 1)
else:
    case1: s from index + 1 to end equals t from index + 1 to end
    case2: s from index to end equals t from index + 1 to end
    case3: s from index + 1 to end equals t from index to end
    return case1 || case2 || case3
Time: O(n)
Space: O(n)
*/
public class OneEditDistance {
	public boolean isOneEditDistance(String s, String t) {
		if (s == null || t == null || Math.abs(s.length() - t.length()) > 1 || s.equals(t)) {
			return false;
		}
		return helper(s, t, 0, 0);
	}

	private boolean helper(String s, String t, int i, int j) {
		if (i == s.length() || j == t.length()) {
			return true;
		}
		if (s.charAt(i) == t.charAt(j)) {
			return helper(s, t, i + 1, j + 1);
		} else {
			return s.substring(i + 1).equals(t.substring(j + 1)) ||
					s.substring(i).equals(t.substring(j + 1)) ||
					s.substring(i + 1).equals(t.substring(j));
		}
	}
}
