package string;

import java.util.HashMap;
import java.util.Map;

public class StrStr {
	public static void main(String[] args) {
		StrStr solution = new StrStr();
		String str1 = "mississippi";
		String str2 = "pi";
		int result = solution.strStr(str1, str2);
		System.out.println(result);
	}
	public int strStr(String str1, String str2) {
		if (str1 == null || str2 == null || str2.length() == 0)
			return 0;

		Map<Character, Integer> map = new HashMap<>();
		for (char c : str2.toCharArray()) {
			map.put(c, map.getOrDefault(c, 0) + 1);
		}
		int matchRemain = map.size();
		int left = 0;
		int right = 0;
		while (right < str1.length()) {
			char c = str1.charAt(right);
			if (map.containsKey(c)) {
				int count = map.get(c) - 1;
				if (count == 0)
					matchRemain--;
				else if (count == -1)
					matchRemain++;

				map.put(c, count);
			}
			if (right >= str2.length()) {
				char c2 = str1.charAt(right - str2.length());
				if (map.containsKey(c2)) {
					int count = map.get(c2) + 1;
					if (count == 1)
						matchRemain++;
					else if (count == 0)
						matchRemain--;

					map.put(c2, count);
				}
				left++;
			}
			if (matchRemain == 0)
				return left;

			right++;
		}
		return -1;
	}
}
