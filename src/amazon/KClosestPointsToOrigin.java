package amazon;
/*
We have a list of points on the plane.  Find the K closest points to the origin (0, 0).

(Here, the distance between two points on a plane is the Euclidean distance.)
You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)

Example 1:

Input: points = [[3,3],[5,-1],[-2,4]], K = 2
Output: [[3,3],[-2,4]]
(The answer [[-2,4],[3,3]] would also be accepted.)

Note:\
1 <= K <= points.length <= 10000
-10000 < points[i][0] < 10000
-10000 < points[i][1] < 10000
 */

import java.util.PriorityQueue;

/*
1. put all points into a heap
2. define comparator
3. poll from heap k times to get results
Time: nlogk
Space: n
*/
public class KClosestPointsToOrigin {
	public int[][] kClosest(int[][] points, int K) {
		if (points == null || points.length == 0 || K <= 0) {
			return new int[0][0];
		}
		K = Math.min(K, points.length);
		PriorityQueue<int[]> pq = new PriorityQueue<>(K, (a, b) -> {
			int dist1 = (int) Math.pow(a[0], 2) + (int) Math.pow(a[1], 2);
			int dist2 = (int) Math.pow(b[0], 2) + (int) Math.pow(b[1], 2);
			return Integer.valueOf(dist1).compareTo(dist2);
		});
		for (int[] point : points) {
			pq.offer(point);
		}
		int[][] result = new int[K][2];
		for (int i = 0; i < K; i++) {
			result[i] = pq.poll();
		}
		return result;
	}
}
