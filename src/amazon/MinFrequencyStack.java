package amazon;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/*
Implement FreqStack, a class which simulates the operation of a stack-like data structure.

FreqStack has two functions:
push(int x), which pushes an integer x onto the stack.
pop(), which removes and returns the most frequent element in the stack.
If there is a tie for most frequent element, the element closest to the top of the stack is removed and returned.

Example:
Input:
["FreqStack","push","push","push","push","push","push","pop","pop","pop","pop"],
[[],[5],[7],[5],[7],[4],[5],[],[],[],[]]
Output: [null,null,null,null,null,null,null,5,7,5,4]

Explanation:
After making six .push operations, the stack is [5,7,5,7,4,5] from bottom to top.  Then:

pop() -> returns 5, as 5 is the most frequent.
The stack becomes [5,7,5,7,4].

pop() -> returns 7, as 5 and 7 is the most frequent, but 7 is closest to the top.
The stack becomes [5,7,5,4].

pop() -> returns 5.
The stack becomes [5,7,4].

pop() -> returns 4.
The stack becomes [5,7].
 */
/*
freqMap: <number, count>
int maxFreq
stackMap<freq, stack>

push:
1. increase count in freqMap, update maxFreq
2. push num into corresponding stack in stackMap

pop:
1. find corresponding stack in stackMap with maxFreq
2. top of corresponding stack is the number to return
3. decrease count of this number in freqMap
4. if stack is empty, decrease maxFreq

Time: O(1) for both push and pop
Space: O(n)
*/
public class MinFrequencyStack {
    private Map<Integer, Integer> freqMap;
    private int maxFreq = 0;
    private Map<Integer, Deque<Integer>> stackMap;

    public MinFrequencyStack() {
        freqMap = new HashMap<>();
        stackMap = new HashMap<>();
    }

    public void push(int x) {
        int freq = freqMap.getOrDefault(x, 0) + 1;
        freqMap.put(x, freq);
        maxFreq = Math.max(maxFreq, freq);

        //Deque<Integer> deque = stackMap.getOrDefault(freq, new ArrayDeque<>());
        //deque.offerFirst(x);
        //stackMap.put(freq, deque);
        stackMap.computeIfAbsent(freq, k -> new ArrayDeque<>()).offerFirst(x); // use "computeIfAbsent" instead of "getOrDefault"
    }

    public int pop() {
        Deque<Integer> deque = stackMap.get(maxFreq);
        int num = deque.pollFirst();
        freqMap.put(num, freqMap.get(num) - 1);
        if (deque.isEmpty()) {
            maxFreq--;
        }
        return num;
    }
}
