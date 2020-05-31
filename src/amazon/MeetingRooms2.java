package amazon;

import java.util.Arrays;

/*
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

Example 1:
Input: [[0, 30],[5, 10],[15, 20]]
Output: 2
Example 2:

Input: [[7,10],[2,4]]
Output: 1
 */
/*
1. decouple each start and end pair and create new object (time, type)
2. sort this new object array by time asc, end before start
3. linear scan new object array
    when see a start, room count++
    when see an end, room count--
return room count

Time: NlogN
Space: N
*/
public class MeetingRooms2 {
	static class Node {
		int time;
		boolean type; // true: start; false: end

		Node(int time, boolean type) {
			this.time = time;
			this.type = type;
		}
	}

	public int minMeetingRooms(int[][] intervals) {
		if (intervals == null || intervals.length == 0) {
			return 0;
		}
		Node[] array = buildArray(intervals);
		Arrays.sort(array, (a, b) -> {
			if (a.time != b.time) {
				return Integer.valueOf(a.time).compareTo(b.time);
			}
			return a.type ? 1 : -1; // if both have the same time, End goes before Start
		});
		int roomCount = 0;
		int maxRoomCount = 0;
		for (Node node : array) {
			if (node.type) {
				roomCount++;
				maxRoomCount = Math.max(maxRoomCount, roomCount);
			} else {
				roomCount--;
			}
		}
		return maxRoomCount;
	}

	private Node[] buildArray(int[][] intervals) {
		int length = intervals.length;
		Node[] array = new Node[length * 2];
		for (int i = 0; i < length; i++) {
			array[i] = new Node(intervals[i][0], true);
			array[i + length] = new Node(intervals[i][1], false);
		}
		return array;
	}
}
