package leetcode;

public class _5__LongestPalindrome {
    public String longestPalindrome(String s) {
        if(s.length() == 0 || s.length() ==1) return s;
        if(s.length()==2){
            if (s.charAt(0)==s.charAt(1)) return s;
        }
        char ch[] = init(s);
        int longest = 0;
        int index = 0;
        int tempt;
        for (int i = 1; i < ch.length - 1; i++) {
            tempt = check(ch, i);
            if (tempt > longest) {
                longest = tempt;
                index = i;
            }
        }
        String str = "";
        int count=longest*2;
        for (int i = index - longest; count>=0 ; i++) {
            if (ch[i]!='\u0000')str += ch[i];
            count--;
        }
        str = str.trim();
        System.out.println(str);
        return str;
    }

    private char[] init(String s) {
        char ch[] = new char[s.length() * 2 + 1];
        int t = 0;
        for (int i = 1; i < s.length() * 2; i += 2) {
            ch[i] = s.charAt(t);
            t++;
        }
        return ch;
    }

    private int check(char[] ch, int index) {
        int count = 1;
        while (index - count >= 0 && index + count < ch.length) {
            if (ch[index-count] == ch[index+count]){
                count++;
            }else{
                break;
            }
        }
        return count-1;
    }
}
