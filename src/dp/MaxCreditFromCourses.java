package dp;

import java.util.Arrays;

/*
dfs:
case1: not take current course: dfs(i) = dfs(i + 1)
case2: take current course(if not conflict): dfs(i) = credit[i] + dfs(i + 1)
return max(case1, case2)
 */
public class MaxCreditFromCourses {
    public int getMaxCredit(int[][] courses, int[] credits) {
        // ignore corner cases
        Arrays.sort(courses, (a, b) -> a[0] < b[0] ? -1 : 1);
        return helper(0, 0, courses, credits);
    }
    private int helper(int endTime, int index, int[][] courses, int[] credits) {
        if (index == courses.length) {
            return 0;
        }
        int credit1 = helper(endTime, index + 1, courses, credits);
        int credit2 = 0;
        if (endTime <= courses[index][0]) {
            credit2 = credits[index] + helper(courses[index][1], index + 1, courses, credits);
        }
        return Math.max(credit1, credit2);
    }
}