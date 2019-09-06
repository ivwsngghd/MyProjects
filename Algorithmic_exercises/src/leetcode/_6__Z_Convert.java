package leetcode;

public class _6__Z_Convert {
    public String convert(String s, int numRows) {
        if (numRows == 1) return s;

        StringBuilder ret = new StringBuilder();
        int n = s.length();
        int cycleLen = 2 * numRows - 2;  // (numRows - 2) + numRows (这里算的是第一行，其他行数到对应的行数再减)   //循环长度的这公式怎么来的.....

        for (int i = 0; i < numRows; i++) {     //共有多少行
            for (int j = 0; j + i < n; j += cycleLen) {     // 如果当前行数小于numRows，遍历这一行的所有字母
                ret.append(s.charAt(j + i));                // 先加上第几行的首字母
                if (i != 0 && i != numRows - 1 && j + cycleLen - i < n) //如果不是第一行，不是最后一行，而且加上循环数减去当前行数(下标，所以要保证下标的相对大小)
                    ret.append(s.charAt(j + cycleLen - i));         //获取当前行的下一个字母
            }
        }
        return ret.toString();
    }
}
