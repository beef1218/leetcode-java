package amazon;

import java.util.HashMap;
import java.util.Map;

/*
similar to LRU
1. use a doubly linkedlink to store appeared chars in order. head: least recent; tail: most recent
2. use a hashmap <char, ListNode>
for each char:
    a. if not in map: add to tail, add to map
    b. if in map and value not null: delete from linkedlist, set map value to null
    c. if in map and value is null: do nothing
return: tail != null ? char at tail : null
 */
public class FirstNonRepeatingCharInStream {
    static class ListNode {
        char value;
        ListNode next;
        ListNode prev;
        ListNode(char value) {
            this.value = value;
        }
    }
    private Map<Character, ListNode> map;
    private ListNode head;
    private ListNode tail;

    public FirstNonRepeatingCharInStream() {
        map = new HashMap<>();
    }

    public void read(char ch) {
        if (!map.containsKey(ch)) {
            addNewChar(ch);
        } else if (map.get(ch) == null) {
            return;
        } else {
            markDup(ch);
        }
    }

    public Character firstNonRepeating() {
        return head == null ? null : head.value;
    }

    private void addNewChar(char ch) {
        ListNode node = new ListNode(ch);
        if (tail == null) {
            tail = node;
            head = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        map.put(ch, node);
    }

    private void markDup(char ch) {
        ListNode node = map.get(ch);
        if (node == head)
            head = node.next;

        if (node == tail)
            tail = node.prev;

        if (node.next != null)
            node.next.prev = node.prev;

        if (node.prev != null)
            node.prev.next = node.next;

        map.put(ch, null);
    }
}
