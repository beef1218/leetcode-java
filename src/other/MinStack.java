package other;

/*
Implement a stack with getMin() function. Requires O(1) time.

1. Use one stack to store elements
2. use another stack to store <min, size of stack1 when this min element was inserted>
 */

import java.util.ArrayDeque;
import java.util.Deque;

public class MinStack {
	public static void main(String[] args) {
		MinStack minStack = new MinStack();
		minStack.push(-2);
		minStack.push(0);
		minStack.push(-3);
		int a = minStack.getMin();
		System.out.println(minStack.getMin());
	}
	static class Node {
		int min;
		int size;

		Node(int min, int size) {
			this.min = min;
			this.size = size;
		}
	}

	private Deque<Integer> deque;
	private Deque<Node> minDeque;

	public MinStack() {
		deque = new ArrayDeque<>();
		minDeque = new ArrayDeque<>();
	}

	public void push(int x) {
		deque.offerFirst(x);
		if (minDeque.isEmpty() || x < minDeque.peekFirst().min) {
			minDeque.offerFirst(new Node(x, deque.size()));
		}
	}

	public void pop() {
		if (deque.size() == 0) {
			return;
		}
		deque.pollFirst();
		if (minDeque.peekFirst().size > deque.size()) {
			minDeque.pollFirst();
		}
	}

	public int top() {
		if (deque.size() == 0) {
			return 0;
		}
		return deque.peekFirst();
	}

	public int getMin() {
		if (deque.size() == 0) {
			return 0;
		}
		return minDeque.peekFirst().min;
	}
}
