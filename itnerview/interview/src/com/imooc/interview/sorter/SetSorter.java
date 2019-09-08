package com.imooc.interview.sorter;

import java.util.*;

public class SetSorter {
    public static void main(String[] args) {
        TreeSet<Employee> emps = new TreeSet<Employee>(/*new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return (int)(o2.getSalary() - o1.getSalary());
            }
        }*/);
        emps.add(new Employee("张三", 33, 1800f));
        emps.add(new Employee("李四", 55, 3800f));
        emps.add(new Employee("王五", 40, 2300f));
        System.out.println(emps);
    }
}
