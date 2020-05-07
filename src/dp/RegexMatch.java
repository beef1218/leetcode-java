package dp;

import org.junit.Assert;
import org.junit.Test;

/*
10. Regular Expression Matching

dp[i][j]: whether s from [0, i-1] match p from [0, j-1]
dp[0][0]: true
dp[0][1]: false
dp[0][j]: if (dp[0][j-2] && p at j-1 is *): true; else: false
dp[i][0]: false

dp[i][j]: case1: s at i-1 == p at j-1 || p at j-1 is '.': dp[i-1][j-1]
          case2: p at j-1 == '*':
            2a: zero instance of j-2: if (dp[i][j-2]): true
            2b: 1 or more instance of j-2: if (dp[i-1][j]): true
            else: false
          case3: p at j-1 is neither '.' nor '*' and != s at i-1: false
return dp[s.length()][p.length()]

      i-1,j-1  i-1,j
i,j-2           i,j

top to bottom, left to right

*/ 
public class RegexMatch {

	@Test
	public void TestIsMatch() {
		RegexMatch solution = new RegexMatch();
		Assert.assertFalse(solution.isMatch("abcd", "d*"));
	}

	public boolean isMatch(String s, String p) {
		if (s == null && p == null)
			return true;

		if (s == null || p == null)
			return false;

		boolean[][] dp = new boolean[2][p.length() + 1];
		for (int j = 0; j <= p.length(); j++) {
			if (j == 0)
				dp[0][j] = true;
			else if (j == 1)
				dp[0][j] = false;
			else
				dp[0][j] = p.charAt(j - 1) == '*' && dp[0][j - 2];
		}
		for (int i = 1; i <= s.length(); i++) {
			for (int j = 0; j <= p.length(); j++) {
				if (j == 0) {
					dp[i%2][j] = false;
					continue;
				}
				char c = p.charAt(j - 1);
				if (c == '.' || c == s.charAt(i - 1))
					dp[i%2][j] = dp[(i - 1)%2][j - 1];
				else if (c == '*')
					dp[i%2][j] = dp[i%2][j - 2]
							|| ((s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') && dp[(i - 1)%2][j]);
				else
					dp[i%2][j] = false;
			}
		}
		return dp[(s.length())%2][p.length()];
	}
}
