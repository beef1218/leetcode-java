package fb;
/*
Implement pow(x, n), which calculates x ^ n.
 */
public class Pow {
	public double myPow(double x, int n) {
		if (x == 0) {
			return 0;
		}
		// in case n = Integer.MIN_VALUE, -n will overflow, so we use a long variable here
		long N = n;
		if (n < 0) {
			x = 1 / x;
			N = -N;
		}
		return helper(x, N);
	}

	private double helper(double x, long N) {
		if (N == 0) {
			return 1.0;
		}
		double num = helper(x, N / 2);
		if (N % 2 == 0) {
			num *= num;
		} else {
			num *= num * x;
		}
		return num;
	}
}
