package leetcode;

public class _8__Atoi {
    public static int myAtoi(String str) {
        str = str.trim();       //去除空格
        if (str.length() == 0 || str == null) return 0;  //空串返回0
        boolean isFlag = false; // 用于记录第一个非数字字符是否符号"-"或者"+"
        if (str.charAt(0) < 48 || str.charAt(0) > 58)
            if (str.charAt(0) == '+' || str.charAt(0) == '-') isFlag = true;
            else return 0;
        int isPos = 0;  //符号是否为负数"-"
        boolean hasNum = false; // 符号后面是否有数字
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char temp = str.charAt(i);
            if (hasNum && !(temp >= 48 && temp <= 58)) break;  //有数字后遇到第一个非数字即终止
            if (temp == '-') isPos = -1;    //判断是否为负数  (是否对"-"的位置有要求)
            if(isFlag && i>0 && !(temp >= 48 && temp <= 58)) break; // 判断是否存在+- 算符并存的情况

            if (temp >= 48 && temp <= 58) {
                stringBuilder.append(temp);
                hasNum = true;
            }
        }

        int result;
        try {
            if (stringBuilder.toString().length()==0) return 0;
            result = Integer.parseInt(stringBuilder.toString());
        } catch (Exception e) {
            if (isPos == 0) return Integer.MAX_VALUE;
            else return Integer.MIN_VALUE;
        }
        if (isPos == -1) result = result * -1;
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE);
        System.out.println("RESULT: " + myAtoi("+-2"));
    }
}

//这道题的意思其实就是寻找一个连续的数字带符号的数字，无论是符号还是数字都不能中断
