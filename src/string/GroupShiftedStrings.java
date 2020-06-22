package string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:
"abc" -> "bcd" -> ... -> "xyz"
Given a list of non-empty strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.

Example:
Input: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
Output:
[
  ["abc","bcd","xyz"],
  ["az","ba"],
  ["acef"],
  ["a","z"]
]
 */
/*
Create my own hashCode() for each string:

 */
public class GroupShiftedStrings {
	public List<List<String>> groupStrings(String[] strings) {
		List<List<String>> result = new ArrayList<>();
		if (strings == null || strings.length == 0) {
			return result;
		}
		Map<Integer, List<String>> strMap = new HashMap<>();
		for (String s : strings) {
			int hash = hashCode(s);
			strMap.computeIfAbsent(hash, (k) -> new ArrayList<>()).add(s);
		}
		for (List<String> value : strMap.values()) {
			result.add(value);
		}
		return result;
	}

	private int hashCode(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		int result = 0;
		for (int i = 0; i < s.length() - 1; i++) {
			char c1 = s.charAt(i);
			char c2 = s.charAt(i + 1);
			int diff = c2 > c1 ? c1 - c2 + 26 : c1 - c2; // make sure the diff is within [0, 25]
			result = result * 26 + diff;
		}
		result += s.length() * 17; // take length of the string into consideration
		return result;
	}
}
