package fb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/*
Design a data structure that supports all following operations in average O(1) time.

insert(val): Inserts an item val to the set if not already present.
remove(val): Removes an item val from the set if present.
getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
 */
/*
hashmap: <integer, index>
ArrayList: list of integer

insert: add to end of arraylist, add to hashmap
remove:
1. get index from map
2. swap it with last integer. delete last integer
3. update index in map for the previous last integer. remove int form map

random:
1. generate random index from range [0, size)
2. return that number from list
*/
public class RandomizedSet {
	private Map<Integer, Integer> map;
	private List<Integer> list;

	public RandomizedSet() {
		map = new HashMap<>();
		list = new ArrayList<>();
	}

	/**
	 * Inserts a value to the set. Returns true if the set did not already contain the specified element.
	 */
	public boolean insert(int val) {
		if (map.containsKey(val)) {
			return false;
		}
		list.add(val);
		map.put(val, list.size() - 1);
		return true;
	}

	/**
	 * Removes a value from the set. Returns true if the set contained the specified element.
	 */
	public boolean remove(int val) {
		Integer index = map.get(val);
		if (index == null) {
			return false;
		}
		int num = list.get(list.size() - 1);
		list.set(index, num);
		list.remove(list.size() - 1);
		map.put(num, index);
		map.remove(val);
		return true;
	}

	/**
	 * Get a random element from the set.
	 */
	public int getRandom() {
		//int index = (int)(Math.random() * list.size());
		int index = new Random().nextInt(list.size());
		return list.get(index);
	}
}
