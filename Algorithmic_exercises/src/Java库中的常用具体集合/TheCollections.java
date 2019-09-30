package Java库中的常用具体集合;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class TheCollections {
    public static void main(String[] args) {
        List arrayList = new ArrayList();
        List linkedList = new LinkedList();

        Queue arrayQueue = new ArrayDeque();    //用循环数组实现的双端队列
        Queue<Student> priorityQueue = new PriorityQueue<>();    //可以高效删除最小元素的集合；底层原理是堆
        PriorityQueue<Student> newPriorityQueue = new PriorityQueue<>((Comparator<Student>) (stu1, stu2) -> stu1.getAge() - stu2.getAge());
                                                                //还可以自己手写比较器，实现比较的方法

        TreeSet treeSet = new TreeSet(); //有序集合

        LinkedHashMap<String,Student> linkedHashMap = new LinkedHashMap<>();

        ConcurrentHashMap<String,Student> hashMap = new ConcurrentHashMap<>();

        Stack<Student> students = new Stack<>();

    }
}

class Student{
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
