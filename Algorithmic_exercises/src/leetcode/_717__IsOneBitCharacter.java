package leetcode;

public class _717__IsOneBitCharacter {
    public boolean isOneBitCharacter(int[] bits) {
        int x =0;
        int i = bits.length ;
        if(i == 0) return false ;
        if(i == 1) return true;
        for(x = 0 ; x <= i-2 ; x++){
            if(bits[x] == 1) x++ ;
        }
        if(x != i-1) return false;

        return true;
    }
}
