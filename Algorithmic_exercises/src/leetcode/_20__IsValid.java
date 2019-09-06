package leetcode;

import java.util.Stack;

public class _20__IsValid {
    public boolean isValid(String s) {
        if (s.length() % 2 != 0) return false;

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (stack.size() > 0) {
                if (isValid(stack.peek(),s.charAt(i) )) {
                    stack.pop();
                    continue;
                }
            }
            stack.push(s.charAt(i));
        }


        return stack.isEmpty();
    }

    private static boolean isValid(Character left, Character right) {
        if (left == '(' && right == ')') return true;
        if (left == '[' && right == ']') return true;
        if (left == '{' && right == '}') return true;
        return false;
    }
}
