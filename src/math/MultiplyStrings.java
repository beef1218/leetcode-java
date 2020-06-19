package math;

/*
Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.
Note:
The length of both num1 and num2 is < 110.
Both num1 and num2 contain only digits 0-9.
Both num1 and num2 do not contain any leading zero, except the number 0 itself.
You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */
/*
  123
x  55
------
  615
 615
________
 6665

 1. use an array of length m + n to store result
 2. use nested for loops to calculate the product of each pair of digits
 3. for a product of digits at i and j, add the carry at i + j + 1, that's the current number
 4. add number % 10 to i + j + 1; add number / 10 to i + j
 5. handle leading zero
*/
public class MultiplyStrings {
	public String multiply(String num1, String num2) {
		if (num1.equals("0") || num2.equals("0")) {
			return "0";
		}
		int length1 = num1.length();
		int length2 = num2.length();
		int[] array = new int[length1 + length2];

		// important: the nested for loops must iterate from least important digit to most important digit
		for (int i = length1 - 1; i >= 0; i--) {
			for (int j = length2 - 1; j >= 0; j--) {
				int num = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
				int sum = array[i + j + 1] + num;
				array[i + j] += sum / 10;
				array[i + j + 1] = sum % 10;
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = array[0] == 0 ? 1 : 0; i < array.length; i++) {
			sb.append(array[i]);
		}
		return sb.toString();
	}
}
