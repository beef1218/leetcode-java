package string;
/*
Implement atoi which converts a string to an integer.

The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.

The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.

If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.

If no valid conversion could be performed, a zero value is returned.

Note:

Only the space character ' ' is considered as whitespace character.
Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. If the numerical value is out of the range of representable values, INT_MAX (231 − 1) or INT_MIN (−231) is returned.
Example 1:

Input: "42"
Output: 42
Example 2:

Input: "   -42"
Output: -42
Explanation: The first non-whitespace character is '-', which is the minus sign.
             Then take as many numerical digits as possible, which gets 42.
Example 3:

Input: "4193 with words"
Output: 4193
Explanation: Conversion stops at digit '3' as the next character is not a numerical digit.
Example 4:

Input: "words and 987"
Output: 0
Explanation: The first non-whitespace character is 'w', which is not a numerical
             digit or a +/- sign. Therefore no valid conversion could be performed.
Example 5:

Input: "-91283472332"
Output: -2147483648
Explanation: The number "-91283472332" is out of the range of a 32-bit signed integer.
             Thefore INT_MIN (−231) is returned.
 */
/*
1. trim input
2. if first char is neither digit nor + / -, return 0
3. else, keep the sigh (optional), get number, check overflow. if overflow, return max / min
4. return number
*/
public class StringToInteger {
	public static void main(String[] args) {
		String input = "-91283472332";
		int result = new StringToInteger().myAtoi(input);
		System.out.println(result);
	}

	public int myAtoi(String str) {
		if (str == null)
			return 0;

		str = str.trim();
		if (str.length() == 0)
			return 0;

		char firstChar = str.charAt(0);
		int i = 0;
		boolean positive = true;
		if (firstChar != '-' && firstChar != '+' && !Character.isDigit(firstChar))
			return 0;

		if (firstChar == '-' || firstChar == '+') {
			positive = firstChar == '+';
			i++;
		}

		long num = 0;
		for (; i < str.length(); i++) {
			char c = str.charAt(i);
			if (!Character.isDigit(c))
				break;

			num = num * 10 + c - '0';
			if (num > Integer.MAX_VALUE)
				return positive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
		}
		return positive ? (int) num : (int) -num;
	}
}
