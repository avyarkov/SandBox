package leetcode;

import java.util.Arrays;

// LeetCode 2300
public class SuccessfulPotions {

    public static int[] successfulPairs(int[] spells, int[] potions, long success) {
        int n = spells.length, m = potions.length;
        int[] pairs = new int[n];

        Arrays.sort(potions);

        for (int i = 0; i < n; i++) {
            int l = -1, r = m;
            while (r - l > 1) {
                int middle = (l + r) / 2;
                long res = (long) spells[i] * potions[middle];
                if (res >= success) {
                    r = middle;
                } else {
                    l = middle;
                }
            }
            pairs[i] = m - r;
        }

        return pairs;
    }

    public static void main(String[] args) {
        int[] spells = {5, 1, 3}, potions = {1, 2, 3, 4, 5};
        long success = 7;
        System.out.println(Arrays.toString(successfulPairs(spells, potions, success)));
    }
}
