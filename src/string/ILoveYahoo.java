package string;

public class ILoveYahoo {
	public String reverseWords(String input) {
		if (input == null || input.length() <= 1)
			return input;

		char[] array = input.toCharArray();
		reverse(array, 0, input.length() - 1);
		int left = 0;
		for (int right = 1; right < array.length; right++) {
			if (array[right - 1] == ' ')
				left = right;
			if (array[right] == ' ')
				reverse(array, left, right - 1);
			else if (right == array.length - 1)
				reverse(array, left, right);
		}
		return new String(array);
	}

	private void reverse(char[] array, int left, int right) {
		while (left < right) {
			char tmp = array[left];
			array[left] = array[right];
			array[right] = tmp;
			left++;
			right--;
		}
	}
}
