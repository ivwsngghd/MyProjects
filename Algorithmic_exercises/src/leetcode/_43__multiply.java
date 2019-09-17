package leetcode;

import java.util.Stack;

public class _43__multiply {
    public static void main(String[] args) {
        String num1 = "5423396";
        String num2 = "5424012638";
        System.out.println(multiply(num1, num2));

    }

    public static String multiply(String num1, String num2) {
        if (num1 == null || num2 == null || num1.equals("0")  || num2.equals("0")) return "0";
        Stack<Integer> stack = new Stack<>();
        String result = null;

        for (int i = num1.length() - 1; i >= 0; i--) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = num2.length() - 1, carry = 0; j >= 0; j--) {

            }
            stack.add(null);
        }


        return "" + result;
    }


}
