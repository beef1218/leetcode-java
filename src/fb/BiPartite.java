package fb;

import java.util.ArrayDeque;
import java.util.Queue;

/*
Given an undirected graph, return true if and only if it is bipartite.
Recall that a graph is bipartite if we can split it's set of nodes into two independent subsets A and B such that every edge in the graph has one node in A and another node in B.
The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j exists.
Each node is an integer between 0 and graph.length - 1.  There are no self edges or parallel edges: graph[i] does not contain i, and it doesn't contain any element twice.

Example 1:
Input: [[1,3], [0,2], [1,3], [0,2]]
Output: true
Explanation:
The graph looks like this:
0----1
|    |
|    |
3----2
We can divide the vertices into two groups: {0, 2} and {1, 3}.
Example 2:
Input: [[1,2,3], [0,2], [0,1,3], [0,2]]
Output: false
Explanation:
The graph looks like this:
0----1
| \  |
|  \ |
3----2
We cannot find a way to divide the set of nodes into two independent subsets.
 */
/*
2 groups: group1, group2
use an array to store the group of each node
1. start with node zero. if this node has no group yet, assign into group1.
2. do bfs. pull from queue, for each neighbor:
    2.1. if has group, check isValid. if invalid, return false; else, do nothing
    2.2. if no group, assign opposite group, add to queue
    terminate when queue is empty
return true at the end

Time: V + E
Space: V
*/
public class BiPartite {
	public boolean isBipartite(int[][] graph) {
		if (graph == null || graph.length == 0) {
			return true;
		}
		int[] group = new int[graph.length];
		for (int i = 0; i < graph.length; i++) {
			if (!helper(graph, i, group)) {
				return false;
			}
		}
		return true;
	}

	private boolean helper(int[][] graph, int index, int[] group) {
		if (group[index] != 0) {
			return true;
		}
		group[index] = 1;
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(index);
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			int neiGroup = group[cur] == 1 ? 2 : 1;
			for (int nei : graph[cur]) {
				if (group[nei] != 0) {
					if (group[nei] != neiGroup) {
						return false;
					}
				} else {
					group[nei] = neiGroup;
					queue.offer(nei);
				}
			}
		}
		return true;
	}
}
