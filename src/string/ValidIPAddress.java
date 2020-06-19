package string;
/*
Write a function to check whether an input string is a valid IPv4 address or IPv6 address or neither.
IPv4 addresses are canonically represented in dot-decimal notation, which consists of four decimal numbers, each ranging from 0 to 255, separated by dots ("."), e.g.,172.16.254.1;
Besides, leading zeros in the IPv4 is invalid. For example, the address 172.16.254.01 is invalid.
IPv6 addresses are represented as eight groups of four hexadecimal digits, each group representing 16 bits. The groups are separated by colons (":").
For example, the address 2001:0db8:85a3:0000:0000:8a2e:0370:7334 is a valid one.
Also, we could omit some leading zeros among four hexadecimal digits and some low-case characters in the address to upper-case ones, so 2001:db8:85a3:0:0:8A2E:0370:7334 is also a valid IPv6 address(Omit leading zeros and using upper cases).
However, we don't replace a consecutive group of zero value with a single empty group using two consecutive colons (::) to pursue simplicity.
For example, 2001:0db8:85a3::8A2E:0370:7334 is an invalid IPv6 address.

Besides, extra leading zeros in the IPv6 is also invalid. For example, the address 02001:0db8:85a3:0000:0000:8a2e:0370:7334 is invalid.
Note: You may assume there is no extra space or special characters in the input string.

Example 1:
Input: "172.16.254.1"
Output: "IPv4"
Explanation: This is a valid IPv4 address, return "IPv4".

Example 2:
Input: "2001:0db8:85a3:0:0:8A2E:0370:7334"
Output: "IPv6"\
Explanation: This is a valid IPv6 address, return "IPv6".

Example 3:
Input: "256.256.256.256"
Output: "Neither"
Explanation: This is neither a IPv4 address nor a IPv6 address.
 */
/*
check v4:
1. split by "\\." (need to check trailing .)
2. length needs to be 4
2. for each chunk, cannot have zero length.
3. if has leading zero and length is more than 1, return false
4. for each chunk, it must be within [0, 255]. (use parseInt, need to check for "+" and "-")
check v6:
1. split by ":" (need to check trailing :)
2. length needs to be 8
3. each chunk has length within [1, 4]
2. each chunk must only have letters or digits. (if use parseInt(chunk, 16), need to check for "+" and "-")
*/

public class ValidIPAddress {
	public static void main(String[] args) {
		ValidIPAddress solution = new ValidIPAddress();
		String input = "172.16.254.1";
		String input2 = "2001:0db8:85a3:0:0:8A2E:0370:7334:";
		System.out.println(solution.validIPAddress(input2));
	}

	public String validIPAddress(String IP) {
		if (IP == null) {
			return "Neither";
		}
		if (isv4(IP)) {
			return "IPv4";
		} else if (isv6(IP)) {
			return "IPv6";
		} else {
			return "Neither";
		}
	}

	private boolean isv4(String ip) {
		if (ip.endsWith(".")) {
			return false;
		}
		String[] array = ip.split("\\.");
		if (array.length != 4) {
			return false;
		}
		for (String chunk : array) {
			if (chunk.length() == 0 || chunk.length() != 1 && chunk.charAt(0) == '0') {
				return false;
			}
			if (chunk.contains("+") || chunk.contains("-")) {
				return false;
			}
			try {
				int num = Integer.parseInt(chunk);
				if (num < 0 || num > 255) {
					return false;
				}
			} catch (NumberFormatException e) {
				return false;
			}
		}
		return true;
	}

	private boolean isv6(String ip) {
		if (ip.endsWith(":")) {
			return false;
		}
		String[] array = ip.split(":");
		if (array.length != 8) {
			return false;
		}
		for (String chunk : array) {
			if (chunk.length() < 1 || chunk.length() > 4) {
				return false;
			}
			if (chunk.contains("+") || chunk.contains("-")) {
				return false;
			}
			try {
				Integer.parseInt(chunk, 16);
			} catch (NumberFormatException e) {
				return false;
			}
		}
		return true;
	}
}
