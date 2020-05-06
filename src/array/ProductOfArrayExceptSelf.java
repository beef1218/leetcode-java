package array;
/*
method1: do prefix product arrays
     [1, 2, 3, 4]
left [1, 1, 2, 6]
right[24, 12, 4, 1]
Time: O(n)
Space: O(n)

method2: use result array itself for prefix product
Time: O(n)
Space: O(1) (output does not count)
*/
public class ProductOfArrayExceptSelf {
	public int[] productExceptSelf(int[] nums) {
		if (nums == null || nums.length <= 1)
			return new int[0];

		int[] result = new int[nums.length];
		result[0] = 1;
		for (int i = 1; i < nums.length; i++) {
			result[i] = result[i - 1] * nums[i - 1];
		}
		int rightProduct = 1;
		for (int i = nums.length - 2; i >= 0; i--) {
			rightProduct *= nums[i + 1];
			result[i] *= rightProduct;
		}
		return result;
	}
}
