package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwoSumAllPair1 {
	public List<List<Integer>> allPairs(int[] array, int target) {
		List<List<Integer>> result = new ArrayList<>();
		Map<Integer, List<Integer>> map = new HashMap<>();
		for (int i = 0; i < array.length; i++) {
			int cur = array[i];
			// need to add result before putting cur into the map to avoid using self more than once
			if (map.containsKey(target - cur)) {
				List<Integer> list2 = map.get(target - cur);
				for (int num : list2) {
					result.add(Arrays.asList(num, i));
				}
			}
			List<Integer> list = map.getOrDefault(cur, new ArrayList<>());
			list.add(i);
			map.put(cur, list);
		}
		return result;
	}
}
