package dp;
/*
A message containing letters from A-Z is being encoded to numbers using the following ways:
         ‘A’ = 1
         ‘B’ = 2
         …
         ‘Z’ = 26

Given an encoded message containing digits, determine the total number of ways to decode it.
Input:    “212”
It can be either decoded as 2,1,2("BAB") or 2,12("BL") or 21,2("UB"), return 3.
 */
/*
corner case: leading zero

dp[i]: number of ways to decode from input [0, i]
dp[0]: 1
dp[i]: sum of:
(single) if input at i is not zero: dp[i-1]
(double) if input [i-1, i] is between [10, 26]: if i == 1 ? 1 : dp[i-2]

return dp[input.length() - 1]

Time: O(n)
Space: O(1)
*/
public class DecodeWays {
	public static void main(String[] args) {
		DecodeWays solution = new DecodeWays();
		String input = "59305";
		System.out.println(input.length());
		int result = solution.numDecodeWayDP(input);
		System.out.println(result);
	}

	public int numDecodeWayDP(String input) {
		if (input == null || input.length() == 0 || input.charAt(0) == '0')
			return 0;

		int[] dp = new int[3];
		dp[0] = 1;
		for (int i = 1; i < input.length(); i++) {
			dp[i % 3] = 0;
			if (input.charAt(i) != '0')
				dp[i % 3] += dp[(i - 1) % 3];

			char c1 = input.charAt(i - 1);
			char c2 = input.charAt(i);
			if (c1 == '1' || c1 == '2' && c2 >= '0' && c2 <= '6')
				dp[i % 3] += i == 1 ? 1 : dp[(i - 2) % 3];
		}
		return dp[(input.length() - 1) % 3];

	}

	public int numDecodeWayDFS1(String input) {
		if (input == null || input.length() == 0 || input.charAt(0) == '0')
			return 0;

		int[] result = new int[]{0};
		dfs(0, input, result);
		return result[0];
	}

	private void dfs(int index, String input, int[] result) {
		if (index == input.length()) {
			result[0]++;
			return;
		}
		char c1 = input.charAt(index);
		//single
		if (c1 != '0')
			dfs(index + 1, input, result);

		if (index < input.length() - 1) {
			//double
			char c2 = input.charAt(index + 1);
			if (c1 == '1' || c1 == '2' && c2 >= '0' && c2 <= '6')
				dfs(index + 2, input, result);
		}
	}

	public int numDecodeWayDFS2(String input) {
		if (input == null || input.length() == 0 || input.charAt(0) == '0')
			return 0;

		return dfs(0, input);
	}

	private int dfs(int index, String input) {
		if (index >= input.length())
			return 1;

		if (input.charAt(index) == '0') {
			return 0;
		}
		int result = dfs(index + 1, input);
		if (index < input.length() - 1) {
			char c1 = input.charAt(index);
			char c2 = input.charAt(index + 1);
			if (c1 == '1' || c1 == '2' && c2 >= '0' && c2 <= '6')
				result += dfs(index + 2, input);
		}
		return result;
	}
}
