package Java的一些特殊的语言特性;

import java.util.ArrayList;
import java.util.List;

public class draftDemo {
    public static void main(String[] args) {
        List<String> list2[] = (List<String>[]) new List[5];
        list2[0] = new ArrayList<>();
        list2[0].add("2333");

        List<String> list1[] = (List<String>[])new Object();
        list1[0] = new ArrayList<>();
        list1[0].add("233333");
    }
}

class Student{
    public static void say(){
        System.out.println("blablabla");
    }

}
