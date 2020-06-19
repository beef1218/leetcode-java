package fb;

import java.util.ArrayList;
import java.util.List;

/*
Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
You may assume that the intervals were initially sorted according to their start times.

Example 1:
Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
 */
/*
1. use a list to store result
2. need a boolean flag "inserted" to indicate whether new interval has been inserted
3. for each interval
	3.1. if inserted, insert and continue
    3.2. if not overlap and interval.end < newinterval.start, add interval to list
    3.3. if not overlap and interval.start > newinterval.end, add new interval to list, set inserted = true, insert interval
    3.4. if overlap: update new interal, continue
*/
public class InsertInterval {
	public int[][] insert(int[][] intervals, int[] newInterval) {
		// assume inputs are valid, not null, not empty
		//Arrays.sort(intervals, (a, b) -> Integer.valueOf(a[0]).compareTo(b[0]));
		List<int[]> list = new ArrayList<>();
		boolean inserted = false;
		for (int[] interval : intervals) {
			if (inserted) {
				list.add(interval);
				continue;
			}
			if (isOverlap(interval, newInterval)) {
				newInterval[0] = Math.min(interval[0], newInterval[0]);
				newInterval[1] = Math.max(interval[1], newInterval[1]);
			} else if (interval[1] < newInterval[0]) {
				list.add(interval);
			} else {
				list.add(newInterval);
				inserted = true;
				list.add(interval);
			}
		}
		if (!inserted) {
			list.add(newInterval);
		}
		return list.toArray(new int[list.size()][]);
	}
	private boolean isOverlap(int[] interval1, int[] interval2) {
		return interval1[0] <= interval2[1] && interval1[1] >= interval2[0];
	}
}
