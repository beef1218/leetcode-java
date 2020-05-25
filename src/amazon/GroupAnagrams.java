package amazon;

import java.util.*;

/*
Given an array of strings, group anagrams together.

Example:
Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
Output:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]
Note:
All inputs will be in lowercase.
The order of your output does not matter.
 */
/*
assume no duplicate words.

1. use a hashmap, store <ana: list of words>
2. for each word, sort, add to map
3. construct result from hashmap

n number of words, longest word has length m
Time: m * n * logm
Space: m * n
*/
public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0)
            return new ArrayList<>();;

        Map<String, List<String>> wordMap = new HashMap<>();
        for (String s : strs) {
            char[] array = s.toCharArray();
            Arrays.sort(array);
            String ana = new String(array);
            List<String> list = wordMap.getOrDefault(ana, new ArrayList<>());
            list.add(s);
            wordMap.put(ana, list);
        }
        return new ArrayList<>(wordMap.values());
    }
}
