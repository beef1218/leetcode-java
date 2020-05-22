package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Determine if there exists three elements in a given array that sum to the given target number. Return all the triple of values that sums to target.

Assumptions
The given array is not null and has length of at least 3
No duplicate triples should be returned, order of the values in the tuple does not matter
Examples

A = {1, 2, 2, 3, 2, 4}, target = 8, return [[1, 3, 4], [2, 2, 4]]
 */
/*
1. sort array
2. i < j < k; for each i, do twoSum from i + 1 to end;
3. Pay attention to dedup

Time: O(n^2)
Space: O(n)
 */
public class ThreeSum {
	public List<List<Integer>> allTriples(int[] array, int target) {
		List<List<Integer>> result = new ArrayList<>();
		if (array == null || array.length < 3)
			return result;

		Arrays.sort(array);
		for (int i = 0; i < array.length - 2; i++) {
			twoSum(array, i, target - array[i], result);
			while (i + 1 < array.length - 2 && array[i + 1] == array[i])   // need dedup any following number that are equal to array[i]
				i++;
		}
		return result;
	}

	private void twoSum(int[] array, int prefixIndex, int target, List<List<Integer>> result) {
		int left = prefixIndex + 1;
		int right = array.length - 1;
		while (left < right) {
			int sum = array[left] + array[right];
			if (left - 1 > prefixIndex && array[left - 1] == array[left] || sum < target) // need to dedup and check whether out of bound
				left++;
			else if (right + 1 < array.length && array[right + 1] == array[right] || sum > target) // need to dedup and check whether out of bound
				right--;
			else
				result.add(Arrays.asList(array[prefixIndex], array[left++], array[right--]));
		}
	}
}
