package dfs;

import java.util.ArrayList;
import java.util.List;

/*
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
Find all strobogrammatic numbers that are of length = n.

Example:
Input:  n = 2
Output: ["11","69","88","96"]
 */
/*
use a char array of length n, do dfs
number of levels: n / 2
at each level, put a pair of number at index and n - index - 1
if n is odd number, the middle number can only be 0/1/8, handled in recursion base case.
Time: n * 5 ^ (n / 2)
Space: n
 */
public class StrobogrammaticNumber2 {
	private char[] singles = new char[]{'0', '1', '8'};
	private String[] pairs = new String[]{"00", "11", "69", "96", "88"};

	public List<String> findStrobogrammatic(int n) {
		List<String> result = new ArrayList<>();
		if (n <= 0) {
			return result;
		}
		char[] array = new char[n];
		helper(0, array, result);
		return result;
	}

	private void helper(int index, char[] array, List<String> result) {
		if (index == array.length / 2) {
			if (array.length % 2 == 0) {
				result.add(new String(array));
			} else {
				// the digit in the middle can only be 0 / 1 / 8
				for (int i = 0; i < singles.length; i++) {
					array[index] = singles[i];
					result.add(new String(array));
				}
			}
			return;
		}
		for (String pair : pairs) {
			if (index == 0 && pair.charAt(0) == '0') {
				continue;
			}
			array[index] = pair.charAt(0);
			array[array.length - index - 1] = pair.charAt(1);
			helper(index + 1, array, result);
		}
	}
}
