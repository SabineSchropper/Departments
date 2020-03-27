package com.company;

import java.util.ArrayList;

public class Department {

    String departmentName;
    ArrayList<Department> departmentArrayList = new ArrayList<>();
    ArrayList<Employee> employeeArrayList = new ArrayList<>();
    String myPartlyOutput;


    public Department(String departmentName, Employee employee) {
        this.departmentName = departmentName;
        employeeArrayList.add(employee);
        employee.setDepartment(this);
    }

    public void addSubDepartment(Department subDepartment) {
        if (!departmentArrayList.contains(subDepartment)) {
            departmentArrayList.add(subDepartment);
        }
    }

    public String printDepartment(String myOutput, String myTabs) {
        String myTab = myTabs + "\t";
        myPartlyOutput = myOutput;
        for (Department department : departmentArrayList) {
            myPartlyOutput = myPartlyOutput + myTab + department.departmentName;
            for (Employee employee : department.employeeArrayList) {
                myPartlyOutput = myPartlyOutput + "  " + employee.name;
            }
            myPartlyOutput = myPartlyOutput + "\n ";
            myPartlyOutput = department.printDepartment(myPartlyOutput,myTab);
        }
        return myPartlyOutput;
    }


    public void addEmployee(Employee employee){
        if (!employeeArrayList.contains(employee)){
            employeeArrayList.add(employee);
            employee.setDepartment(this);
        }
    }

    public void switchDepartment(String personWantsToSwitch, Department switchDestination){

        for(Employee employee : employeeArrayList){
            if (employee.name.equalsIgnoreCase(personWantsToSwitch)){
                switchDestination.addEmployee(employee);
                employeeArrayList.remove(employee);
                break;
            }
            else{
                System.out.println("Something went wrong.");
            }
        }
    }
}

