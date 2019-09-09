package com.imooc.interview._synchronized自旋锁和自适应锁和锁消除和锁粗化;

//锁粗化
public class CoarseSync {
    public static String copyString100Times(String target){
        int i = 0;
        StringBuffer sb = new StringBuffer();
        //发现重复对象大量加减锁monitorenter 和 monitorexit,编译器在进行逃逸分析(有无其他线程对它操作)后会进行优化---只加一次锁；
        while (i<100){
            sb.append(target);
        }
        return sb.toString();
    }
}
