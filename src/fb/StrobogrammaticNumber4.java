package fb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Find all the confusing numbers that's less or equal to n.
A confusing number is a number that is still valid and has a different value when rotated 180 degree.
 */
/*
digits: 0, 1, 6, 9, 8
1. start with one of the digits (except 0), keep adding digits.
2. check if is confusing number. if it is, add to result.
3. when out of range, return
 */
public class StrobogrammaticNumber4 {
	public List<Integer> findConfusingNumbers(int n) {
		List<Integer> result = new ArrayList<>();
		if (n < 1) {
			return result;
		}
		Map<Integer, Integer> map = getMap();
		for (int num : map.keySet()) {
			if (num == 0) { // exclude leading zero
				continue;
			}
			dfs(num, n, result, map);
		}
		return result;
	}
	private void dfs(int cur, int n, List<Integer> result, Map<Integer, Integer> map) {
		if (cur > n) {
			return;
		}
		if (isConfusing(cur, map)) {
			result.add(cur);
		}
		for (int num : map.keySet()) {
			dfs(cur * 10 + num, n, result, map);
		}
	}
	private boolean isConfusing(int num, Map<Integer, Integer> map) {
		int tmp = num;
		int rev = 0;
		while (tmp > 0) {
			int digit = map.get(tmp % 10);
			tmp /= 10;
			rev = rev * 10 + digit;
		}
		return num != rev;
	}
	private Map<Integer, Integer> getMap() {
		Map<Integer, Integer> map = new HashMap<>();
		map.put(0, 0);
		map.put(1, 1);
		map.put(6, 9);
		map.put(9, 6);
		map.put(8, 8);
		return map;
	}
}
