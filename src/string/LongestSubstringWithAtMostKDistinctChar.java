package string;

import java.util.HashMap;
import java.util.Map;

/*
Given a string, find the length of the longest substring T that contains at most k distinct characters.

Example 1:

Input: s = "eceba", k = 2
Output: 3
Explanation: T is "ece" which its length is 3.
 */
/*
sliding window with a hashmap <char, count>. left and right are the borders of the window
1. add char at right to map
2. while map size > k, reduce count of char at left in map, when count reaches zero, remove that entry.
3. update global max
return global max
*/
public class LongestSubstringWithAtMostKDistinctChar {
	public int lengthOfLongestSubstringKDistinct(String s, int k) {
		if (s == null || s.length() == 0 || k == 0) {
			return 0;
		}
		Map<Character, Integer> map = new HashMap<>();
		int result = 0;
		int left = 0;
		for (int right = 0; right < s.length(); right++) {
			char cR = s.charAt(right);
			map.put(cR, map.getOrDefault(cR, 0) + 1);
			while (map.size() > k) {
				char cL = s.charAt(left);
				int count = map.get(cL);
				if (count == 1) {
					map.remove(cL);
				} else {
					map.put(cL, count - 1);
				}
				left++;
			}
			result = Math.max(result, right - left + 1);
		}

		return result;
	}
}
