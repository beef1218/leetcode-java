package fb;

import java.util.ArrayList;
import java.util.List;

/*
two pointers: i, j to point to the two interval arrays.
for interval1 and interval2:
start = max(interval1.start, interval2.start)
end = min(interval1.end, interval2.end)
if (start <= end), there is an intersection. add it to result
move the smaller pointer
*/
public class IntervalsIntersections {
	public int[][] intervalIntersection(int[][] A, int[][] B) {
		List<int[]> resultList = new ArrayList<>();
		int i = 0;
		int j = 0;
		while (i < A.length && j < B.length) {
			int start = Math.max(A[i][0], B[j][0]);
			int end = Math.min(A[i][1], B[j][1]);
			if (start <= end) {
				resultList.add(new int[]{start, end});
			}
			if (A[i][1] < B[j][1]) {
				i++;
			} else if (A[i][1] > B[j][1]) {
				j++;
			} else {
				i++;
				j++;
			}
		}
		return resultList.toArray(new int[resultList.size()][]);
	}
}
