package dp;

import java.util.Arrays;
/*
Given a time range, and a list of courses you can take. Each course has its start and end time, and its credit.
You cannot take more than one course at any time. What is the maximum credit you can get?

Example:
Range = [1, 10)
courses:
[0, 3), [1, 5), [2, 3), [4, 8), [5, 12)
   5       2       1       3       4

Assumption:
inputs are valid
 */

public class MaxCreditFromCourses {
	public static void main(String[] args) {
		int[][] courses = new int[][]{{-1, 3}, {1, 5}, {2, 3}, {4, 8}, {5, 12}};
		int[] credits = new int[]{5, 2, 1, 3, 4};
		MaxCreditFromCourses solution = new MaxCreditFromCourses();
		int result = solution.getMaxCredit1(0, 10, courses, credits);
		int result2 = solution.getMaxCredit2(0, 10, courses, credits);
		int result3 = solution.getMaxCredit3(0, 10, courses, credits);
		System.out.println(result);
		System.out.println(result2);
		System.out.println(result3);
	}

	static class Course {
		int start;
		int end;
		int credit;

		Course(int start, int end, int credit) {
			this.start = start;
			this.end = end;
			this.credit = credit;
		}
	}

	/*
    recursion + memoization (backwards)
    helper function returns max credit from courses [i, end]
    base case: when the end is reached, return 0
    recursive rule:
    case1: not take ith course: helper(i + 1, endTime)
    case2: take ith course(if not conflict): credit[i] + helper(i + 1, i's end)
    return max(case1, case2)

    Note: when going from back to front, courses need to be sorted by start time

    n: number of courses; k: range of start to end time
    Time: O(n * k)
    Space: O(n * k)
     */
	public int getMaxCredit1(int startTime, int endTime, int[][] input, int[] credits) {
		// ignore corner cases
		Course[] courses = new Course[input.length];
		for (int i = 0; i < input.length; i++) {
			courses[i] = new Course(input[i][0], input[i][1], credits[i]);
		}
		Arrays.sort(courses, (a, b) -> a.start < b.start ? -1 : 1);
		int[][] memo = new int[courses.length][endTime + 1];
		return helper(startTime, endTime, 0, courses, memo);
	}

	private int helper(int curStart, int totalEnd, int index, Course[] courses, int[][] memo) {
		if (index == courses.length || courses[index].end > totalEnd) {
			return 0;
		}
		if (memo[index][curStart] != 0) {
			return memo[index][curStart];
		}
		//not take current course
		int credit1 = helper(curStart, totalEnd, index + 1, courses, memo);
		int credit2 = 0;

		//if current course can be taken
		if (curStart <= courses[index].start) {
			credit2 = courses[index].credit + helper(courses[index].end, totalEnd, index + 1, courses, memo);
		}
		memo[index][curStart] = Math.max(credit1, credit2);
		return memo[index][curStart];
	}

	/*
	DP method1:
	DP[i]: max credit from course [0, i] with course i taken

	base case:
	DP[0]: credit[0]

	induction rule:
	DP[i]: credit[i] + DP[k] where kth end <= ith start
	return max(DP[i])

	Note: when going from start to end, courses need to be sorted by end time

	Time: O(n ^ 2)
	Space: O(n)
	 */
	public int getMaxCredit2(int startTime, int endTime, int[][] input, int[] credits) {
		Course[] courses = new Course[input.length];
		for (int i = 0; i < input.length; i++) {
			courses[i] = new Course(input[i][0], input[i][1], credits[i]);
		}
		Arrays.sort(courses, (a, b) -> a.end < b.end ? -1 : 1);
		int[] dp = new int[courses.length];
		dp[0] = startTime <= courses[0].start ? courses[0].credit : 0;
		int maxCredit = dp[0];
		for (int i = 1; i < courses.length; i++) {
			if (courses[i].end > endTime) { // already out of time range
				break;
			}
			if (courses[i].start < startTime) { // current course is before our range start time
				continue;
			}
			dp[i] = courses[i].credit;
			for (int j = 0; j < i; j++) {
				if (courses[j].end <= courses[i].start) {
					dp[i] = Math.max(dp[i], dp[j] + courses[i].credit);
				}
			}
			maxCredit = Math.max(maxCredit, dp[i]);
		}
		return maxCredit;
	}

	/*
	DP method2:
	DP[i]: max credit from course [0, i] (ith course not have to be taken) (DP[i] >= DP[i - 1])

	base case:
	DP[0]: credit[0]

	induction rule:
	DP[i]: case1: not take ith course: DP[i - 1]
		   case2: take ith course: credit[i] + DP[j] where j's end <= i's start  (can use binary search to find k)

	return DP[input.length - 1]

	Note: when going from start to end, courses need to be sorted by end time

	Time: O(nlogn)
	Space: O(n)
	 */
	public int getMaxCredit3(int startTime, int endTime, int[][] input, int[] credits) {
		Course[] courses = new Course[input.length];
		for (int i = 0; i < input.length; i++) {
			courses[i] = new Course(input[i][0], input[i][1], credits[i]);
		}
		Arrays.sort(courses, (a, b) -> a.end < b.end ? -1 : 1);
		int[] dp = new int[courses.length];
		dp[0] = courses[0].start >= startTime ? courses[0].credit : 0;
		for (int i = 1; i < courses.length; i++) {
			if (courses[i].end > endTime) {
				return dp[i - 1];
			}
			int curCourseCredit = courses[i].start >= startTime ? courses[i].credit : 0;
			int j = getPreviousCourse(courses, courses[i].start);
			if (j == -1) {
				dp[i] = Math.max(dp[i - 1], curCourseCredit);
			} else {
				dp[i] = Math.max(dp[i - 1], curCourseCredit + dp[j]);
			}
		}
		return dp[courses.length - 1];
	}

	// binary search to find the latest course whose end <= target
	private int getPreviousCourse(Course[] courses, int target) {
		int left = 0;
		int right = target - 1;
		while (left < right - 1) {
			int mid = left + (right - left) / 2;
			if (courses[mid].end > target) {
				right = mid - 1;
			} else {
				left = mid;
			}
		}
		if (courses[right].end <= target) {
			return right;
		} else if (courses[left].end <= target) {
			return left;
		}
		return -1;
	}
}