package fb;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

Note:
The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:
Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
 */
/*
1. put dict into a hashset
2. start cutting the input from left (dp solution)

dp[i]: input from 0 to i can break
i from 0 to end
    if [0, i] in set, dp[i] is true
    j from 1 to i (important, j means the cut is before jth char, which means j needs reach i)
        if [j, i] in set and dp[j] is true, dp[i] is true

return dp[end]
Time: n^2
Space: n plus the wordDict size
*/
public class WordBreak {
	public boolean wordBreak(String s, List<String> wordDict) {
		if (wordDict == null || wordDict.size() == 0 || s == null || s.length() == 0)
			return false;

		boolean[] dp = new boolean[s.length()];
		Set<String> words = new HashSet<>(wordDict);
		for (int i = 0; i < s.length(); i++) {
			if (words.contains(s.substring(0, i + 1))) {
				dp[i] = true;
				continue;
			}
			for (int j = 1; j <= i; j++) {
				if (dp[j - 1] && words.contains(s.substring(j, i + 1))) {
					dp[i] = true;
					break;
				}
			}
		}
		return dp[dp.length - 1];
	}
}
