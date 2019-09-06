package leetcode;

public class _9__IsPalindrome {
    public boolean isPalindrome(int x) {
        if (x<0) return false;
        if (x<10) return  true;
        int check = x;
        int result = 0;
        while(check!=0){
            result *= 10;
            int temp = check%10 ;
            check/=10;
            result = result+temp;
        }
        if(result == x) return true;
        return false;
    }
}
