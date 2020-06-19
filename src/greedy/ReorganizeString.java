package greedy;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*
Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.
If possible, output any possible result.  If not possible, return the empty string.

Example 1:
Input: S = "aab"
Output: "aba"

Example 2:
Input: S = "aaab"
Output: ""
 */
/*
Greedy:
Keep adding the two most frenquent chars into the result.

1. build a hashmap<char, count>
2. use a maxHeap to store chars (from higher count to lower count)
3. use a stringbuilder, keep polling 2 chars from the maxHeap and add to sb. (these will be the two chars with the highest count)
4. decrease the counts of these 2 chars, if their counts > 0, add back to maxHeap
5. if there is only one char left in the maxHeap and its count is > 1, we do not have a valid result
   else, return sb.toString()

Time: O(n * logn)
Space: O(n)
*/
public class ReorganizeString {
	public String reorganizeString(String S) {
		if (S == null || S.length() == 0) {
			return "";
		}
		Map<Character, Integer> charCount = new HashMap<>();
		for (char c : S.toCharArray()) {
			charCount.put(c, charCount.getOrDefault(c, 0) + 1);
		}
		PriorityQueue<Character> pq = new PriorityQueue<>(charCount.size(), (a, b) -> charCount.get(b) - charCount.get(a)); // no need to worry about overflow since these are positive numbers
		pq.addAll(charCount.keySet());
		StringBuilder sb = new StringBuilder();
		while (pq.size() > 1) {
			char first = pq.poll();
			char second = pq.poll();
			sb.append(first);
			sb.append(second);
			charCount.put(first, charCount.get(first) - 1);
			charCount.put(second, charCount.get(second) - 1);
			if (charCount.get(first) > 0) {
				pq.offer(first);
			}
			if (charCount.get(second) > 0) {
				pq.offer(second);
			}
		}
		if (!pq.isEmpty()) {
			char last = pq.poll();
			if (charCount.get(last) > 1) {
				return "";
			}
			sb.append(last);
		}
		return sb.toString();
	}
}
