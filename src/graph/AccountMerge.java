package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.
Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some email that is common to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.
After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.

Example 1:
Input:
accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
Explanation:
The first and third John's are the same person as they have the common email "johnsmith@mail.com".
The second John and Mary are different people as none of their email addresses are used by other accounts.
We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
 */
/*
build a graph. each email is a node. emails from the same account should have a connection
1. map: emailToName<email, name> -> store email to name mapping
2. map: graph<email, List<email> -> a graph with all the nodes belong to the same account connected (does not have to have all the nodes connected each other, as long as there is one connect that we can visit all the nodes, that is enough)
3. use a hashset for visited emails for dedup when doing dfs within the graph
4. after finish building the graph, for each email, do a dfs to get all its neighbors, add to result.
*/
public class AccountMerge {
	public List<List<String>> accountsMerge(List<List<String>> accounts) {
		List<List<String>> result = new ArrayList<>();
		if (accounts == null || accounts.size() == 0) {
			return result;
		}
		Map<String, String> emailToName = new HashMap<>();
		Map<String, List<String>> graph = new HashMap<>();
		Set<String> visited = new HashSet<>();

		for (List<String> account : accounts) {
			String name = account.get(0);
			for (int i = 1; i < account.size(); i++) {
				String email = account.get(i);
				emailToName.put(email, name);
				if (i == 1) { // skip the first email of each account because there is no one to connect yet
					continue;
				}
				graph.computeIfAbsent(email, (k) -> new ArrayList<>()).add(account.get(i - 1)); // connect each email with the previous one, vice versa
				graph.computeIfAbsent(account.get(i - 1), (k) -> new ArrayList<>()).add(email);
			}
		}

		for (String email : emailToName.keySet()) {
			if (!visited.contains(email)) {
				List<String> cur = new ArrayList<>(); // store the results for all emails from the same account
				dfs(email, graph, visited, cur);
				Collections.sort(cur);
				cur.add(0, emailToName.get(email)); // add name to the front of the cur result
				result.add(cur);
			}
		}
		return result;
	}

	private void dfs(String email, Map<String, List<String>> graph, Set<String> visited, List<String> cur) {
		if (!visited.add(email)) {
			return;
		}
		cur.add(email);
		List<String> neighbors = graph.get(email);
		if (neighbors == null) {
			return;
		}
		for (String nei : neighbors) {
			dfs(nei, graph, visited, cur);
		}
	}
}
