package string;

import java.util.ArrayDeque;
import java.util.Deque;

/*
Implement a basic calculator to evaluate a simple expression string.
The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.

Example 1:
Input: "3+2*2"
Output: 7

Example 2:
Input: " 3/2 "
Output: 1

Example 3:
Input: " 3+5 / 2 "
Output: 5
 */
/*
1. trim input
2. use a stack, linear scan. prevOper init to '+'
3. extract each number; when each non digit is reached, process the number with prevOper; update prevOper
4. process (switch prevOper):
    a. +: push num
    b. -: push -num
    c. * or /: poll, calculate, push
5. get sum from the stack and return
*/
public class BasicCalculator2 {
	public int calculate(String s) {
		if (s == null || s.trim().length() == 0)
			return 0;

		s = s.trim();
		Deque<Integer> deque = new ArrayDeque<>();
		int num = 0;
		char prevOper = '+';
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == ' ')
				continue;

			if (!Character.isDigit(c)) {
				calculate(num, deque, prevOper);
				num = 0;
				prevOper = c;
				continue;
			}
			num = num * 10 + c - '0';
			if (i == s.length() - 1) {
				calculate(num, deque, prevOper);
				num = 0;
			}
		}
		return getSum(deque);
	}

	private void calculate(int num, Deque<Integer> deque, char prevOper) {
		switch (prevOper) {
			case '+':
				deque.offerFirst(num);
				break;
			case '-':
				deque.offerFirst(-num);
				break;
			case '*':
				deque.offerFirst(deque.pollFirst() * num);
				break;
			case '/':
				deque.offerFirst(deque.pollFirst() / num);
				break;
			default: // do nothing
		}
	}

	private int getSum(Deque<Integer> deque) {
		int result = 0;
		while (!deque.isEmpty())
			result += deque.pollFirst();

		return result;
	}
}
