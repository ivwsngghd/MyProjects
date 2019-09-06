package leetcode;

public class _202__HappyNum {
    public boolean isHappy(int n) {
        int s = n ;
        if(s<0) return false ;
        if(s==1) return true ;
        while(s!=1 && s!=89){
            s=sum(s);
            if(s == 1) return true ;
            if(s==89) return false ;
        }
        return false ;
    }
    int sum(int s){
        int sum ;
        for(sum = 0 ; s>0 ; s/=10){
            sum += (s%10) * (s%10);
        }
        return sum ;
    }
}
