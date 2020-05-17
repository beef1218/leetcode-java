package amazon;
/*
Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

I can be placed before V (5) and X (10) to make 4 and 9.
X can be placed before L (50) and C (100) to make 40 and 90.
C can be placed before D (500) and M (1000) to make 400 and 900.
Given an integer, convert it to a roman numeral. Input is guaranteed to be within the range from 1 to 3999.
 */
/*
1. form roman array and number array, from the largest to smallest
2. from the largest to smallest number, keep deducting from input, add to output
*/
public class IntegerToRoman {
	public String intToRoman(int num) {
		if (num <= 0)
			return "";

		String[] roman = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
		int[] number = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < roman.length; i++) {
			while (num >= number[i]) {
				sb.append(roman[i]);
				num -= number[i];
			}
		}
		return sb.toString();
	}
}
