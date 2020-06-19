package string;

/*
In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.
Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographicaly in this alien language.

Example 1:
Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
Output: true
Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
 */
/*
1. use an int[26] to store orders of letters
2. check each pair of words
    find the first distinct chars(c1, c2)
        order[c1 - 'a'] < order[c2 - 'a']
*/
public class AlienDictionary2 {
	public boolean isAlienSorted(String[] words, String order) {
		if (words == null || words.length == 0 || order == null || order.length() == 0) {
			return false;
		}
		int[] orderArr = new int[26];
		for (int i = 0; i < order.length(); i++) {
			orderArr[order.charAt(i) - 'a'] = i;
		}
		for (int i = 0; i < words.length - 1; i++) {
			String word1 = words[i];
			String word2 = words[i + 1];
			if (word1.length() > word2.length() && word1.startsWith(word2)) {
				return false; // handle case like "abc" - "ab"
			}
			for (int j = 0; j < word1.length() && j < word2.length(); j++) {
				char c1 = word1.charAt(j);
				char c2 = word2.charAt(j);
				if (c1 == c2) {
					continue;
				}
				if (orderArr[c1 - 'a'] > orderArr[c2 - 'a']) {
					return false;
				}
				break;
			}
		}
		return true;
	}
}
