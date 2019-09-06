package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class _交税的问题 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        in.next();


        while(in.hasNext()){
            System.out.println(Math.round(calTax(in.next())));
        }
    }

    public static double calTax(String  pay) {
        double payment = Double.valueOf(pay);
        if (payment > 85000) return ((payment - 85000) * 0.45 + 20840);
        if (payment > 60000 && payment <= 85000) return ((payment - 60000) * 0.35 + 12090);
        if (payment > 40000 && payment <= 60000) return ((payment - 40000) * 0.3 + 6090);
        if (payment > 30000 && payment <= 40000) return ((payment - 30000) * 0.25 + 3590);
        if (payment > 17000 && payment <= 30000) return ((payment - 17000) * 0.2 + 990);
        if (payment > 8000 && payment <= 17000) return ((payment - 8000) * 0.1 + 90);
        if (payment > 5000 && payment <= 8000) return ((payment - 5000) * 0.03);
        return 0;
    }


}

class gradeNum {
    private static Map<Integer, Float> grade = new HashMap<>();
    private static int[] overToPay = new int[]{0, 3000, 12000, 25000, 35000, 55000, 80000};

    static {
        grade.put(0, 0f);
        grade.put(1, 0.1f);
        grade.put(2, 0.2f);
        grade.put(3, 0.25f);
        grade.put(4, 0.3f);
        grade.put(5, 0.35f);
        grade.put(6, 0.45f);
    }

    public static Map<Integer, Float> getGrade() {
        return grade;
    }

    public static int[] getOverToPay() {
        return overToPay;
    }
}
