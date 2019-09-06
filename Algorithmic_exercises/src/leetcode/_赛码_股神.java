package leetcode;

import java.util.Scanner;

public class _赛码_股神 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println(cal(in.nextInt()));
    }

    private static int cal(int day) {
        if (day <= 2) return day;
        int result = 1;
        int plus = 1;
        int pass = 1;

        while (pass < day) {
//            for (int i = 0; i < plus; i++) {
//                result++;
//                pass++;
//                if (pass == day) return result;
//            }
            if (pass + plus >= day){
                result+= day-pass;
                return result;
            }else {
                result += plus;
                pass += plus;
            }

            plus++;
            result--;
            pass++;
        }
        return result;
    }
}




