package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/*
There are a total of numCourses courses you have to take, labeled from 0 to numCourses-1.
Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 */
/*
assume no duplicate in the input
1. process the input and create inDegree array and outDegree hashMap
    inDegree array: index: course; value: number of requirements left for this course
    outDegree: <course, list of courses depend on this course>.
2. for each course in inDegree, if its value is 0, add to queue
3. poll from queue, add to result list. get list of courses from outDegree, reduce their inDegree count by 1. If reaches 0, add to queue.
4. terminate when queue is empty.
5. if result list size == course count, we have a valid order.

Time: V + E
Space: V + E
*/
public class CourseSchedule {
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		int[] inDegree = new int[numCourses];
		Map<Integer, List<Integer>> outDegree = new HashMap<>();
		processInput(prerequisites, inDegree, outDegree);
		List<Integer> order = new ArrayList<>();
		Queue<Integer> queue = new ArrayDeque<>();
		for (int i = 0; i < inDegree.length; i++) {
			if (inDegree[i] == 0) {
				queue.offer(i);
			}
		}
		while (!queue.isEmpty()) {
			int course = queue.poll();
			order.add(course);
			List<Integer> list = outDegree.get(course);
			if (list == null) {
				continue;
			}
			for (int next : list) {
				inDegree[next]--;
				if (inDegree[next] == 0) {
					queue.offer(next);
				}
			}
		}
		return order.size() == numCourses;
	}
	private void processInput(int[][] pres, int[] inDegree, Map<Integer, List<Integer>> outDegree) {
		for (int[] pre : pres) {
			List<Integer> list = outDegree.getOrDefault(pre[1], new ArrayList<>());
			list.add(pre[0]);
			outDegree.put(pre[1], list);
			inDegree[pre[0]]++;
		}
	}
}
