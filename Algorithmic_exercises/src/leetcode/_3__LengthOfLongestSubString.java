package leetcode;

import java.util.HashMap;
import java.util.Map;

public class _3__LengthOfLongestSubString {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() < 2) return s.length();
        int result = 1;
        char[] chars = null;
        chars = s.toCharArray();
        Map map = new HashMap<Character, Character>();

        int count = 0;
        for (int j = 0; j < chars.length; j++) {
            for (int i = j; i < chars.length; i++) {
                if (map.containsKey(chars[i])) {
                    map.clear();
                    count = 0;
                    break;
                } else {
                    map.put(chars[i], chars[i]);
                    count++;
                    if (count > result) result = count;
                }
            }
        }

        return result;
    }
}
