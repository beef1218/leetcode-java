package amazon;

import java.util.HashMap;
import java.util.Map;

/*
Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

Example:
Input: S = "ADOBECODEBANC", T = "ABC"
Output: "BANC"

Note:
If there is no such window in S that covers all characters in T, return the empty string "".
If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
 */
/*
1. put t into a hashmap <char, count>. remaining match count = map.size()
2. linear scan s. i,j are sliding window borders. init: i = j = -1
3. for each char c at j:
    if c in map: decrease count;
        if count == 0: match count--; if match count == 0: update global min with j - i + 1, update left, right;
            while(match count == 0) (c2 at i)
                update global result
                if c2 in map: increase count; if count == 1: match count++
                i++

terminate when j reaches end
*/
public class MinWindowSubstring {
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0)
            return "";

        Map<Character, Integer> map = new HashMap<>();
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int matchCount = map.size();
        int i = 0;
        int min = Integer.MAX_VALUE;
        int left = -1;
        int right = -1;
        for (int j = 0; j < s.length(); j++) {
            char cR = s.charAt(j);
            if (map.containsKey(cR)) {
                map.put(cR, map.get(cR) - 1);
                if (map.get(cR) == 0) {
                    matchCount--;
                    if (matchCount == 0 && j - i + 1 < min) {
                        min = j - i + 1;
                        left = i;
                        right = j;
                    }
                }
            }
            while (matchCount == 0) {
                if (j - i + 1 < min) {
                    min = j - i + 1;
                    left = i;
                    right = j;
                }
                char cL = s.charAt(i);
                if (map.containsKey(cL)) {
                    map.put(cL, map.get(cL) + 1);
                    if (map.get(cL) == 1) {
                        matchCount++;
                    }
                }
                i++;
            }
        }
        return left != -1 ? s.substring(left, right + 1) : "";
    }
}
