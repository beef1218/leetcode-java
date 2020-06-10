package math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConfusingNumber {
	public static void main(String[] args) {
		ConfusingNumber solution = new ConfusingNumber();
		List<Integer> result = solution.findConfusingNumber(50);
		System.out.println(result);
	}
	public List<Integer> findConfusingNumber(int num) {
		List<Integer> result = new ArrayList<>();
		for (int i = 1; i <= num; i++) {
			if (valid(i) && isPalindrome(i)) {
				continue;
			}
			result.add(i);
		}
		return result;
	}

	private boolean valid(int num) {
		Set<Integer> invalidDigits = new HashSet<>(Arrays.asList(2, 3, 4, 5, 7));
		while (num > 0) {
			int digit = num % 10;
			num /= 10;
			if (invalidDigits.contains(digit)) {
				return false;
			}
		}
		return true;
	}

	private boolean isPalindrome(int num) {
		String s = String.valueOf(num);
		int i = 0;
		int j = s.length() - 1;
		while (i < j) {
			char c1 = s.charAt(i);
			char c2 = s.charAt(j);
			if (c1 == '6' && c2 == '9' || c1 == '9' && c2 == '6') {
				continue;
			} else if (c1 == '6' && c2 == '6' || c1 == '9' && c2 == '9') {
				return false;
			} else if (c1 != c2) {
				return false;
			}
			i++;
			j--;
		}
		return true;
	}
}
