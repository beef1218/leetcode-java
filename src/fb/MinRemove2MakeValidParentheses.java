package fb;

import java.util.ArrayDeque;
import java.util.Deque;

/*
Given a string s of '(' , ')' and lowercase English characters.
Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.
Formally, a parentheses string is valid if and only if:

It is the empty string, contains only lowercase characters, or
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.

Example 1:
Input: s = "lee(t(c)o)de)"
Output: "lee(t(c)o)de"
Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
 */
public class MinRemove2MakeValidParentheses {
    /*
    Method1, use a stack
    1. use a stack. keep track of left count
    2. if it's a letter, push to stack
       if it's a left, push, count++
       if it's a right and count > 0, push, count--
       if it's a right and count == 0, discard
       at the end, remove count number of left from the end
    */
    public String minRemoveToMakeValid1(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        Deque<Character> deque = new ArrayDeque<>();
        int left = 0;
        for (char c : s.toCharArray()) {
            if (c != '(' && c != ')') {
                deque.offerFirst(c);
            } else if (c == '(') {
                deque.offerFirst(c);
                left++;
            } else if (left > 0) {
                deque.offerFirst(c);
                left--;
            }
        }
        char[] result = new char[deque.size() - left];
        for (int i = result.length - 1; i >= 0; i--) {
            char c = deque.pollFirst();
            while (left > 0 && c == '(') {
                left--;
                c = deque.pollFirst();
                continue;
            }
            result[i] = c;
        }
        return new String(result);
    }

    /*
    Method2: stringbuilder 2 passes
    1. from left to right, add to sb. with extra right removed
    2. from right to left, add to sb. with extra left removed
    */
    public String minRemoveToMakeValid2(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        StringBuilder sb = helper(s, '(', ')');
        sb = helper(new String(sb.reverse()), ')', '(');
        return sb.reverse().toString();
    }

    private StringBuilder helper(String s, char open, char close) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c != open && c != close) {
                sb.append(c);
            } else if (c == open) {
                sb.append(c);
                count++;
            } else if (count > 0) {
                sb.append(c);
                count--;
            }
        }
        return sb;
    }
}
