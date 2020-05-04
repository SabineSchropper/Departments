package com.company;

import java.util.ArrayList;

public class Department {

    String departmentName;
    ArrayList<Department> subDepartments = new ArrayList<>();
    ArrayList<Employee> employees = new ArrayList<>();
    String myPartlyOutput;

    public Department(String departmentName) {
        this.departmentName = departmentName;
    }
    public void addSubDepartment(Department subDepartment) {
        if (!subDepartments.contains(subDepartment)) {
            subDepartments.add(subDepartment);
        }
    }
    public String printDepartment(String myOutput, String myTabs) {
        String myTab = myTabs + "\t";
        myPartlyOutput = myOutput;
        for (Department department : subDepartments) {
            myPartlyOutput = myPartlyOutput + myTab + department.departmentName;
            for (Employee employee : department.employees) {
                myPartlyOutput = myPartlyOutput + "  " + employee.name;
            }
            myPartlyOutput = myPartlyOutput + "\n ";
            myPartlyOutput = department.printDepartment(myPartlyOutput,myTab);
        }
        return myPartlyOutput;
    }
    public void addEmployeeAndSetDepartment(Employee employee){
        if (!employees.contains(employee)){
            employees.add(employee);
            employee.setDepartment(this);
        }
    }
    public void switchDepartment(String personWantsToSwitch, Department switchDestination){

        for(Employee employee : employees){
            if (employee.name.equalsIgnoreCase(personWantsToSwitch)){
                switchDestination.addEmployeeAndSetDepartment(employee);
                employees.remove(employee);
                break;
            }
            else{
                System.out.println("Something went wrong.");
            }
        }
    }
}

