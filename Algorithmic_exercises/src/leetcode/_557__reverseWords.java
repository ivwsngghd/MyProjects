package leetcode;

public class _557__reverseWords {
    public static String reverseWords(String s) {
        String[] strings = s.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            stringBuilder.append(strings[i]);
            strings[i] = stringBuilder.reverse().toString();
            stringBuilder.delete(0,stringBuilder.length()); //清空
        }
        stringBuilder.append(strings[0]);
        for (int i = 1; i < strings.length; i++) {
            stringBuilder.append(" " + strings[i]);
        }

        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        String str = "Let's take LeetCode contest";
        System.out.println(reverseWords(str));
    }
}
