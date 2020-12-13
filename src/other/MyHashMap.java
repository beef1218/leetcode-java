package other;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MyHashMap<K, V> {
	static class Node<K, V> {
		private final K key;
		private V value;
		Node<K, V> next;

		Node(K key, V value) {
			this.key = key;
			this.value = value;
		}

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}

		public void setValue(V value) {
			this.value = value;
		}
	}

	public static final int DEFAULT_CAPACITY = 11;
	public static final float DEFAULT_LOAD_FACTOR = (float) 0.6;
	private int size = 0;
	private final int capacity;
	private final float loadFactor;
	Node<K, V>[] array;

	public MyHashMap() {
		this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
	}

	public MyHashMap(int capacity, float loadFactor) {
		if (capacity <= 0) {
			throw new IllegalArgumentException("Capacity needs to be greater than zero");
		}
		array = (Node<K, V>[]) (new Node[capacity]);
		this.capacity = capacity;
		this.loadFactor = loadFactor;
	}

	public synchronized V get(K key) {
		int index = getIndex(key);
		Node<K, V> node = array[index];
		while (node != null) {
			if (equalsKey(node.getKey(), key)) {
				return node.getValue();
			}
			node = node.next;
		}
		return null;
	}

	public synchronized V put(K key, V value) {
		int index = getIndex(key);
		Node<K, V> node = array[index];
		while (node != null) {
			if (equalsKey(node.getKey(), key)) {
				V oldValue = node.getValue();
				node.setValue(value);
				return oldValue;
			}
			node = node.next;
		}
		Node<K, V> newHead = new Node(key, value);
		newHead.next = array[index];
		array[index] = newHead;
		size++;
		if (needRehashing()) {
			rehashing();
		}
		return null;
	}

	public synchronized boolean containsKey(K key) {
		int index = getIndex(key);
		Node<K, V> node = array[index];
		while (node != null) {
			if (equalsKey(node.getKey(), key)) {
				return true;
			}
			node = node.next;
		}
		return false;
	}

	private int getIndex(K key) {
		if (key == null) {
			return 0;
		}
		int hash = key.hashCode() & Integer.MAX_VALUE;
		return hash % array.length;
	}

//	private boolean equalsKey(K key1, K key2) {
//		return key1 == key2 || key1 != null && key1.equals(key2);
//	}
	private boolean equalsKey(K key1, K key2) {
		return Objects.equals(key1, key2);
	}

	private synchronized boolean needRehashing() {
		float ratio = (size + 0.0f) / capacity;
		return ratio >= loadFactor;
	}

	private void rehashing() {
		Node<K, V>[] newArray = (Node<K, V>[])(new Node[size * 2]);
		Arrays.stream(array).forEach(head -> {
			while (head != null) {
				int index = getIndex(head.key);
				Node<K, V> next = head.next;
				head.next = newArray[index];
				newArray[index] = head;
				head = next;
			}
		});

	}

	public synchronized int size() {
		return size;
	}

	public synchronized boolean isEmpty() {
		return size == 0;
	}
}
