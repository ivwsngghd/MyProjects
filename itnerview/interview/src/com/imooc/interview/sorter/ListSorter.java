package com.imooc.interview.sorter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListSorter {
    public static void main(String[] args) {
        List<Employee> emps = new ArrayList<Employee>();
        emps.add(new Employee("张三", 33, 1800f));
        emps.add(new Employee("李四", 55, 3800f));
        emps.add(new Employee("王五", 40, 2300f));
        Collections.sort(emps, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return (int)(o2.getAge() - o1.getAge());
            }
        });
        System.out.println(emps);
    }
}
