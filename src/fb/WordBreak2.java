package fb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.

Note:
The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.

Example 1:
Input:
s = "catsanddog"
wordDict = ["cat", "cats", "and", "sand", "dog"]
Output:
[
  "cats and dog",
  "cat sand dog"
]

Example 2:
Input:
s = "pineapplepenapple"
wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
Output:
[
  "pine apple pen apple",
  "pineapple pen apple",
  "pine applepen apple"
]
Explanation: Note that you are allowed to reuse a dictionary word.
 */
/*
recursion + memoization
memo: store list of string for each index as the start of the substring

 */
public class WordBreak2 {
	public List<String> wordBreak(String s, List<String> wordDict) {
		Set<String> words = new HashSet<>(wordDict);
		Map<Integer, List<String>> memo = new HashMap<>();
		return dfs(s, 0, words, memo);
	}

	private List<String> dfs(String s, int start, Set<String> dict, Map<Integer, List<String>> memo) {
		if (memo.containsKey(start)) {
			return memo.get(start);
		}

		List<String> rst = new ArrayList<>();
		if (start == s.length()) {
			rst.add(""); // need to put in an empty string in order to get in the loop later
			return rst;
		}

		String curr = s.substring(start);
		for (String word : dict) {
			if (curr.startsWith(word)) {
				List<String> sublist = dfs(s, start + word.length(), dict, memo);
				for (String sub : sublist) {
					rst.add(word + (sub.isEmpty() ? "" : " ") + sub);
				}
			}
		}

		memo.put(start, rst);
		return rst;
	}
}
