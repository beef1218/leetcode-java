package fb;
/*
Given a string, decide whether it is a valid number (numeric)

Examples:
"0" -> true
" 0.1 " -> true
"abc" -> false
"1 a" -> false
"2e10" -> true
 */
/*
1. possible chars: '0' ~ '9', '.', 'e', 'E', '+', '-', ' '
2. requirement for space: should be either leading or trailing, not in the middle. Can use trim()
3. requirement for sign: should be either the first char before E or the first char after E. Nowhere else
Invalid cases for sign:
	1. not first char before E (seenDot || seenNumber) && !seenE
	2. not first char after E: seenE && seenNumber
	3. duplicated signs before E: seenSignBeforeE && !seenE
	4. duplicated signs after E: seenSignAfterE

4. requirement for dot: only before E, only once
Invalid cases for dot:
	1. duplicated dots: seenDot
	2. after E: seenE

5. requirement for e / E: only once, must have number before it and after it
Invalid cases for e / E:
	1. duplicated e: seenE
	2. no number before E: !seenNumber
	3. no number after E: seenE && !seenNumberAfterE (check at the end)
 */
public class IsValidNumberNumeric {
	public boolean isNumber(String s) {
		String str = s.trim();
		boolean seenNumber = false;
		boolean seenSignBeforeE = false;
		boolean seenE = false;
		boolean seenDot = false;
		boolean seenNumberAfterE = false;
		boolean seenSignAfterE = false;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c == '+' || c == '-') {
				if ((seenDot || seenNumber) && !seenE) {
					return false;
				}
				if (seenE && seenNumberAfterE) {
					return false;
				}
				if (seenSignBeforeE && !seenE || seenSignAfterE) {
					return false;
				}
				if (seenE) {
					seenSignAfterE = true;
				} else {
					seenSignBeforeE = true;
				}
			} else if (c == '.') {
				if (seenDot || seenE) {
					return false;
				}
				seenDot = true;
			} else if (c >= '0' && c <= '9') {
				seenNumber = true;
				if (seenE) {
					seenNumberAfterE = true;
				}
			} else if (c == 'e' || c == 'E') {
				if (seenE || !seenNumber) {
					return false;
				}
				seenE = true;
			} else {
				return false;
			}
		}
		if (seenE && !seenNumberAfterE) {
			return false;
		}
		return seenNumber;
	}
}
