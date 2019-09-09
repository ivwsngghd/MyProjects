package com.imooc.interview._synchronized自旋锁和自适应锁和锁消除和锁粗化;

public class StringBufferWithoutSync {
    public void add(String str1, String str2) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("shit shitshits").append("asdasd").append("asdas");
        //StringBuffer是线程安全,由于sb只会在这里的append方法中使用,没有被其他线程引用(编译器会进行逃逸分析，即判断有没有可能多引用，如果没有，则会消掉sychronized操作)
        //因此sb属于不可能共享的资源,JVM会自动消除内部的锁
        StringBuffer sb = new StringBuffer();
        sb.append(str1).append(str2);
    }

    public static void main(String[] args) {
        StringBufferWithoutSync withoutSync = new StringBufferWithoutSync();
        for (int i = 0; i < 1000; i++) {
            withoutSync.add("aaa", "bbb");
        }
    }

}
