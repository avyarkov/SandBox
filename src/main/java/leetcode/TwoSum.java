package leetcode;

import java.util.Arrays;
import java.util.HashMap;

// LeetCode 1
public class TwoSum {
    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> indexByValue = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            indexByValue.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int pair = target - nums[i];
            if (indexByValue.containsKey(pair)) {
                int j = indexByValue.get(pair);
                if (i != j) {
                    return new int[] {i, j};
                }
            }
        }
        throw new IllegalStateException();
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(new int[]{3, 3}, 6)));
    }
}
