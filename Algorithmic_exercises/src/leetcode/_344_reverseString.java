package leetcode;

public class _344_reverseString {
    public static void reverseString(char[] s) {
        if (s.length<=1) return;
        int i = 0;
        int j = s.length-1;
        char temp;
        for (;i<j;i++,j--){
            temp = s[i];
            s[i] = s[j];
            s[j] = temp;
        }

    }

    public static void main(String[] args) {
        char[] chars = new char[]{'a','b','c','d','y','u','i'};
        reverseString(chars);
        for (char i : chars){
            System.out.print(i + " ");
        }
    }
}
