package leetcode;

import java.util.Scanner;

public class _getContinueString网易模拟面试题 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        for(int i = 0;i<=in.nextInt();i++){
            System.out.println(in.next());
        }
        in.hasNext();
//        System.out.println(getConString("DCBA"));
//        System.out.println(getConString("ZABCDEFGX"));
//        System.out.println(getConString("XYZABCDMMMGHIJKLRST"));
//        System.out.println(getConString("XYZABCDMMasdfasdfL ABCDEFGHIJKLMNRST"));
    }

    public static String getConString(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        str = str.trim();   //去除空格

        for (int point = 0; point < str.length(); point++) {    //遍历整个字符串
            stringBuilder.append(str.charAt(point));
            int count = 0;
            for (int i = point; i < str.length() - 1; i++) {    //往后遍历连续字符串
                if (str.charAt(i + 1) - 1 != str.charAt(i)) {   //如果中断则跳出
                    break;
                } else {                                        //计算连续多少个字符
                    count++;
                }
            }
            if (count >= 3) {                                   //如果连续多于等于4个 则加入返回
                stringBuilder.append("-" + str.charAt(point + count));
                point += count;
            }

        }

        return stringBuilder.toString();
    }

}
