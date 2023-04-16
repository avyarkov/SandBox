package leetcode;

import java.util.HashMap;

// LeetCode 3
public class LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String string) {
        if (string.isEmpty()) {
            return 0;
        }
        var s = string.toCharArray();
        var map = new HashMap<Character, Integer>();
        map.put(s[0], 0);
        int left = 0, curLen = 1, maxLen = 1;
        for (int i = 1; i < s.length; i++) {
            char c = s[i];
            if (!map.containsKey(c)) {
                curLen++;
            } else {
                left = Math.max(left, map.get(c) + 1);
                curLen = i - left + 1;
            }
            map.put(c, i);
            if (curLen > maxLen) {
                maxLen = curLen;
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        var thiz = new LongestSubstringWithoutRepeatingCharacters();
        System.out.println(thiz.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(thiz.lengthOfLongestSubstring("bbbbb"));
        System.out.println(thiz.lengthOfLongestSubstring("pwwkew"));
        System.out.println(thiz.lengthOfLongestSubstring("abba"));
    }
}
