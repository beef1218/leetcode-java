package sampling;

import java.util.Random;

/*
Given an array of integers with possible duplicates, randomly output the index of a given target number. You can assume that the given target number must exist in the array.

Example:
int[] nums = new int[] {1,2,3,3,3};
Solution solution = new Solution(nums);

// pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
solution.pick(3);

// pick(1) should return 0. Since in the array only nums[0] is equal to 1.
solution.pick(1);
 */
public class RandomPickIndex {
	private int[] nums;
	private Random ranGen;

	public RandomPickIndex(int[] nums) {
		this.nums = nums;
		ranGen = new Random();
	}

	public int pick(int target) {
		int sample = -1;
		int count = 0;
		for (int i = 0; i < nums.length; i++) {
			int num = nums[i];
			if (num == target) {
				count++;
				if (sample == -1) {
					sample = i;
				} else {
					int random = ranGen.nextInt(count);
					if (random == 0) {
						sample = i;
					}
				}
			}
		}
		return sample;
	}
}
