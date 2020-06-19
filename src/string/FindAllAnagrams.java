package string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
The order of output does not matter.

Example 1:
Input:
s: "cbaebabacd" p: "abc"

Output:
[0, 6]

Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
 */
/*
1. build hashmap with p, remainMatch = map.size(). window length = p.length()
2. i from 0 to end
    if char not in map, do nothing
    if char in map, decrease its count.
        if count == 0, remainMatch--;
        if count == -1, remainMatch++; (not really needed here) (The reason we don't have to check count changes from 0 to -1 is that this is a fix size sliding window)
    if i >= p.length: char2 = s.charAt(i - p.length)
        if char2 not in map, do nothing
        if char2 in map, increase its count.
            if count == 1, remainMatch++;
            if count == 0, remainMatch--; (not really needed here)
    if remainMatch == 0, add (i - p.length() + 1) to result
*/
public class FindAllAnagrams {
	public List<Integer> findAnagrams(String s, String p) {
		List<Integer> result = new ArrayList<>();
		if (s == null || p == null || s.length() < p.length()) {
			return result;
		}
		Map<Character, Integer> map = new HashMap<>();
		for (char c : p.toCharArray()) {
			map.put(c, map.getOrDefault(c, 0) + 1);
		}
		int remainMatch = map.size();
		for (int i = 0; i < s.length(); i++) {
			char c1 = s.charAt(i);
			Integer count = map.get(c1);
			if (count != null) {
				map.put(c1, count - 1);
				if (count == 1) {
					remainMatch--;
				}
			}
			if (i >= p.length()) {
				char c2 = s.charAt(i - p.length());
				count = map.get(c2);
				if (count != null) {
					map.put(c2, count + 1);
					if (count == 0) {
						remainMatch++;
					}
				}
			}
			if (remainMatch == 0) {
				result.add(i - p.length() + 1);
			}
		}
		return result;
	}
}
