package array;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/*
Given a collection of intervals, merge all overlapping intervals.

Example 1:
Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 */
/*
1. sort by 1st element asc
2. compare in pairs. p1, p2
3. if p1.end > p2.start, merge p1 and p2
    p2[0] = min(p1[0], p2[0]); p2[1] = max(p1[1], p2[1])
    set p1[0] to Integer.MIN_VALUE so that we will know this interval is gone
4. construct result, skip min values

Time: O(nlogn)
Space: O(n)
*/
public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0)
            return new int[0][0];

        Arrays.sort(intervals, (a, b) -> Integer.valueOf(a[0]).compareTo(b[0]));
        int count = intervals.length; // keep track of the total count left after merging
        for (int i = 0; i < intervals.length - 1; i++) {
            int[] interval1 = intervals[i];
            int[] interval2 = intervals[i + 1];
            if (interval1[1] < interval2[0]) { // no overlap
                continue;
            }
            interval2[0] = Math.min(interval1[0], interval2[0]);
            interval2[1] = Math.max(interval1[1], interval2[1]);
            interval1[0] = Integer.MIN_VALUE;
            count--; // reduce count by 1 for each merge
        }
        int[][] result = new int[count][2];
        int index = 0;
        for (int i = 0; i < intervals.length; i++) {
            if (intervals[i][0] != Integer.MIN_VALUE) {
                result[index++] = intervals[i];
            }
        }
        return result;
    }

    // using stack
    public int[][] merge2(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[0][0];
        }
        Arrays.sort(intervals, (a, b) -> Integer.valueOf(a[0]).compareTo(b[0]));
        Deque<int[]> deque = new ArrayDeque<>();
        for (int[] interval : intervals) {
            if (deque.isEmpty() || deque.peekFirst()[1] < interval[0]) {
                deque.offerFirst(interval);
            } else {
                deque.peekFirst()[1] = Math.max(deque.peekFirst()[1], interval[1]);
            }
        }
        int[][] result = new int[deque.size()][2];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = deque.pollFirst();
        }
        return result;
    }

    // using linkedlist
    public int[][] merge3(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[0][0];
        }
        Arrays.sort(intervals, (a, b) -> Integer.valueOf(a[0]).compareTo(b[0]));
        LinkedList<int[]> merged = new LinkedList<>();
        for (int[] interval : intervals) {
            if (merged.isEmpty() || merged.getLast()[1] < interval[0]) {
                merged.add(interval);
            } else {
                merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }
}
