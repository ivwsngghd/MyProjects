package leetcode;

public class __LCP2_分数化简 {

    public static void main(String[] args) {
//        int[] cont = new int[]{3, 2, 0, 2};
        int[] cont = new int[]{};
//        __LCP2_分数化简 cl = new __LCP2_分数化简();

//        cl.fraction(cont);
        int []result = fraction(cont);
        System.out.println(result[0] +"  " +result[1]);

    }

    public static int[] fraction(int[] cont) {
        if (cont.length < 2) return new int[]{cont[0],1};
        int length = cont.length;
        Data result = new Data(1, cont[length - 1]);
        for (int i = cont.length - 2; i > 0; i--) {
            result.add(cont[i]);
            result.reverse();
        }
        result.add(cont[0]);

        result.division(gcd(result.down, result.up));

        return new int[]{result.up, result.down};
    }


    private static long gcd(int m, int n) {
        while (true) {
            if ((m = m % n) == 0)
                return n;
            if ((n = n % m) == 0)
                return m;
        }
    }

}

class Data {
    int up;
    int down;

    public Data(int up, int down) {
        this.up = up;
        this.down = down;
    }


    public void add(long num) {
        up += down * num;
    }


    public void reverse() {
        int t = up;
        up = down;
        down = t;
    }

    public void division(long gcdNum) {
        up /= gcdNum;
        down /= gcdNum;
    }
}

