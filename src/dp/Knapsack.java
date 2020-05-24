package dp;
/*
recursion:
for i: case1: take i: values[i] + getMax(i + 1, weights, values, capacity - weights[i])
       case2: not take i: getMax(i + 1, weights, values, capacity)
       return max(case1, case2)

Time: 2^n
Do memoization with int[index][capacity + 1] -> Time: n x c

*/
public class Knapsack {
    public int getMax(int[] weights, int[] values, int capacity) {
        // ignore corner cases

        int[][] memo = new int[weights.length][capacity + 1];
        return getMax(0, weights, values, capacity, memo);
    }
    private int getMax(int index, int[] weights, int[] values, int capacity, int[][] memo) {
        if (index == weights.length)
            return 0;

        if (memo[index][capacity] > 0)
            return memo[index][capacity];

        int total1 = weights[index] > capacity ? 0 : values[index] + getMax(index + 1, weights, values, capacity - weights[index], memo);
        int total2 = getMax(index + 1, weights, values, capacity, memo);
        memo[index][capacity] = Math.max(total1, total2);
        return memo[index][capacity];
    }
}
