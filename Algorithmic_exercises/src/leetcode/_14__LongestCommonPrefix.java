package leetcode;

public class _14__LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        if (strs.length == 1) return strs[0];

        int count = 0;
        while (true) {
            for (int j = 0; j < strs.length; j++) {
                if (count == strs[j].length()) {
                    return strs[j];
                    //如果达到其中一个串的长度，直接中断
                }
                if (strs[j].charAt(count) != strs[0].charAt(count)){
                    return strs[0].substring(0,count);
                }
            }
            count++;
        }
    }
}
