package dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
dfs:
                root (234)
          a       b       c
       d  e  f
    g h i

number of level: length of number
at each level, pick one char from list of the number

Time: O(n * 4 ^ n) n is the length of number
Space: O(n)
*/
public class CombinationOfTelephonePad1 {
	public static void main(String[] args) {
		CombinationOfTelephonePad1 solution = new CombinationOfTelephonePad1();
		String[] result = solution.combinations(23);
		System.out.println(Arrays.toString(result));
	}
	final static String[] PAD = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
	public String[] combinations(int number) {
		if (number < 0)
			return new String[0];

		List<String> result = new ArrayList<>();
		dfs(0, String.valueOf(number), new StringBuilder(), result);
		String[] resultArr = new String[result.size()];
		for (int i = 0; i < resultArr.length; i++)
			resultArr[i] = result.get(i);

		return resultArr;
	}
	private void dfs(int index, String number, StringBuilder sb, List<String> result) {
		if (index == number.length()) {
			result.add(sb.toString());
			return;
		}
		String letters = PAD[number.charAt(index) - '0'];
		if (letters.equals("")) {
			dfs(index + 1, number, sb, result);
		} else {
			for (int i = 0; i < letters.length(); i++) {
				sb.append(letters.charAt(i));
				dfs(index + 1, number, sb, result);
				sb.deleteCharAt(sb.length() - 1);
			}
		}
	}
}
