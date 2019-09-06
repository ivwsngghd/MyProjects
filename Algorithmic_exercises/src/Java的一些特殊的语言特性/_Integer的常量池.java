package Java的一些特殊的语言特性;

public class _Integer的常量池 {
    public static void main(String[] args) {
//        System.out.println("13622220266".matches("[\\d]{11}"));

        Integer integer = new Integer(1000000);
        Integer integer4 = new Integer(1000000);
        System.out.println(System.identityHashCode(integer));
        System.out.println(System.identityHashCode(integer4));

        Integer integer1 = new Integer(1);
        Integer integer2 = new Integer(1);
        System.out.println(System.identityHashCode(integer1));
        System.out.println(System.identityHashCode(integer2));



        Integer integer3 = 1000000;
        Integer integer5 = 1000000;
        System.out.println("integer3的内存地址:"+System.identityHashCode(integer3));
        System.out.println("integer5的内存地址:"+System.identityHashCode(integer5));


        Integer integer6 = 1;
        Integer integer7 = 1;
        System.out.println("integer6的内存地址:"+System.identityHashCode(integer6));
        System.out.println("integer7的内存地址:"+System.identityHashCode(integer7));


        System.out.println(integer == integer4);
        System.out.println(integer == integer3);

        System.out.println(integer1 == integer2);

        System.out.println(integer3 == integer5);

        System.out.println(integer1 == integer6);
        System.out.println(integer6 == integer7);

        /*
        * 总结，使用了new方法则不会进入Cache常量池；
        * 这实际也是装箱操作的基本实现方式:小于1字节的，直接从常量池返回；
        * 大于1字节的，存放在堆中；
        * */

    }
}
