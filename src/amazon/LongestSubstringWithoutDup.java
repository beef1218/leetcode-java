package amazon;

import java.util.HashSet;
import java.util.Set;

/*
Given a string, find the longest substring without any repeating characters and return the length of it.
For example, the longest substring without repeating letters for "bcdfbd" is "bcdf", we should return 4 in this case.
 */
/*
use a hashset to store char of the sliding window; use left and right pointers
if right not exist in set
    add right to set, update max, move right
else
    remove left from set, move left
*/
public class LongestSubstringWithoutDup {
	public int lengthOfLongestSubstring(String s) {
		if (s == null || s.length() == 0)
			return 0;

		int left = 0;
		int right = 0;
		Set<Character> set = new HashSet<>();
		int max = 0;
		while (right < s.length()) {
			if (set.add(s.charAt(right))) {
				max = Math.max(max, right - left + 1);
				right++;
			} else {
				set.remove(s.charAt(left));
				left++;
			}
		}
		return max;
	}
}
