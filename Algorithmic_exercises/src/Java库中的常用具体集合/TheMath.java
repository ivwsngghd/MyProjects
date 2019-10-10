package Java库中的常用具体集合;

import java.math.BigInteger;

public class TheMath {
    public static void main(String[] args) {
        System.out.println(Math.addExact(20,-3));
        System.out.println(Math.addExact(20,-3));

        //大整数的使用

        

    }

    public static BigInteger getFactorial(BigInteger num) {
        /**
         *
         * 1  大于 1 才继续计算
         * 2 while 比 for 少点代码
         * 3 int long 范围有限
         * 4 递归浪费资源，深度太深jvm不支持，StackOverflowError
         * 5 使用更专业的计算包
         */
        BigInteger result = num;
        while(num.compareTo(BigInteger.ONE) > 0) {
            num = num.subtract(BigInteger.ONE);
            result = result.multiply(num);
        }
        return result;
    }

    public static BigInteger getFactorial(String numString) {
        return getFactorial(new BigInteger(numString));
    }

}
