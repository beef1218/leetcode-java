package amazon;

import java.util.HashMap;
import java.util.Map;

/*
hashmap: <key, listNode>
doubly linkedlist (value, key, next, prev). tail is the oldest, head is the newest

1. get: if not in map, return null
        if in map, move listnode to head, return value
2. set: if not in map, add to head (check size limit)
        if in map, update value, move listnode to head

3. when limit is reached, delete listnode at tail, move that node from map, move tail pointer
*/
public class LRUCache<K, V> {
	static class ListNode<K, V> {
		K key;
		V value;
		ListNode<K, V> prev;
		ListNode<K, V> next;

		ListNode(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}

	private Map<K, ListNode<K, V>> map;
	private ListNode<K, V> head;
	private ListNode<K, V> tail;
	public final int limit;
	private int size;

	public LRUCache(int limit) {
		map = new HashMap<>(limit);
		this.limit = limit;
	}

	public V get(K key) {
		ListNode<K, V> node = map.get(key);
		if (node == null) {
			return null;
		}
		deleteNode(node);
		appendHead(node);
		return node.value;
	}

	public void set(K key, V value) {
		ListNode<K, V> node = map.get(key);
		if (node == null) {
			if (size == limit) {
				deleteTail();
			}
			node = new ListNode<>(key, value);
			map.put(key, node);
		} else {
			node.value = value;
			deleteNode(node);
		}
		appendHead(node);
	}

	private void deleteNode(ListNode<K, V> node) {
		if (node == tail) {
			tail = node.prev;
		}
		if (node == head) {
			head = node.next;
		}
		if (node.prev != null) {
			node.prev.next = node.next;
		}
		if (node.next != null) {
			node.next.prev = node.prev;
		}
		size--;
	}

	private void appendHead(ListNode<K, V> node) {
		if (head == null) {
			head = node;
			tail = node;
		} else {
			head.prev = node;
			node.next = head;
			head = node;
		}
		size++;
	}

	private void deleteTail() {
		if (tail == null) {
			return;
		}
		map.remove(tail.key);
		tail = tail.prev;
		if (tail != null) {
			tail.next = null;
		}
		size--;
	}

	public int size() {
		return size;
	}
}
