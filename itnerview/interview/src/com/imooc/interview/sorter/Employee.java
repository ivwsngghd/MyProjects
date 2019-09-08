package com.imooc.interview.sorter;

public class Employee implements Comparable<Employee> {
    private String ename;
    private Integer age;
    private Float salary;

    public Employee() {

    }

    @Override
    public String toString() {
        return "Employee{" +
                "ename='" + ename + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }

    public Employee(String ename, Integer age, Float salary) {
        this.ename = ename;
        this.age = age;
        this.salary = salary;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    @Override
    public int compareTo(Employee o) {
        return o.getAge().compareTo(this.getAge());
    }
}
