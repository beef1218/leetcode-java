package fb;
/*
Given two int values a and b, do a / b without using the divide operation.
 */
/*
Assumptions:
1. both input are int type.
2. ignore overflow case: a = Integer.MIN_VALUE; b = -1 -> overflow
Strategy (binary search):
1. corner cases:
	b == 0: throw exception
	a == 0 || abs(a) < abs(b): return 0
2. handle positive / negative signs
3. start with num = 1, keep multiply num by 2 until num * b is greater than a.
4. do binary search between the window
	left, right, mid = left + ((right - left) >> 1)
	if (mid * b == a): return mid
	if (mid * b < a): left = mid
	if (mid * b > a): right = mid - 1
	exit the loop when having 2 elements left; post processing the remaining two elements

Time: O(log(a/b))
Space: O(1)
 */
public class Divide {
	public int divide(int a, int b) {
		if (b == 0) {
			throw new ArithmeticException("divisor cannot be zero");
		}
		if (Math.abs(a) < Math.abs(b)) {
			return 0;
		}
		boolean isPositive = a > 0 && b > 0 || a < 0 && b < 0;
		int num = binarySearch(a, b);
		return isPositive ? num : -num;
	}
	// similar to binary search in a unknown size array
	private int binarySearch(int a, int b) {
		int left = 1;
		int right = 1;
		while (right * 2 * b < a) {
			left = right;
			right *= 2;
		}
		while (left < right - 1) {
			int mid = left + ((right - left) >> 1);
			if (mid * b == a) {
				return mid;
			} else if (mid * b < a) {
				left = mid;
			} else {
				right = mid - 1;
			}
		}
		if (right * b <= a) {
			return right;
		} else {
			return left;
		}
	}
}
