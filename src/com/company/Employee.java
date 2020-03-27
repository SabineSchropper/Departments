package com.company;

public class Employee {

    String name;
    Department department;

    public Employee(String name){
        this.name = name;
    }
    public void setDepartment(Department department) {
        this.department = department;
    }

    public Department getDepartment() {
        return department;
    }
}
