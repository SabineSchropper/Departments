package com.company;

import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        ArrayList<Employee> employees = new ArrayList<>();
        ArrayList<Department> departments = new ArrayList<>();
        Department department = null;
        boolean doesDepartmentAlreadyExists;
        Department searchedDepartment;
        Department destinationDepartment;

        File myFile = new File("C:\\Users\\DCV\\Documents\\Abteilungen1.txt");
        FileReader fileReader = new FileReader(myFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = null;

        while ((line = bufferedReader.readLine()) != null) {
            doesDepartmentAlreadyExists = false;
            String[] splittedValues = line.split(";");
            String name = splittedValues[0];
            String departmentName = splittedValues[1];

            Employee employee = new Employee(name);
            employees.add(employee);

            for (Department dep : departments) {
                if (dep.departmentName.equalsIgnoreCase(departmentName)) {
                    doesDepartmentAlreadyExists = true;
                    department = dep;
                }
            }
            if (doesDepartmentAlreadyExists) {
                //if departmentName already exists we only add the employee
                department.addEmployeeAndSetDepartment(employee);
            } else {
                //else we create department, add employees and also add department to ArrayList
                department = new Department(departmentName);
                department.addEmployeeAndSetDepartment(employee);
                departments.add(department);
            }
        }
        FileReader fileReader1 = new FileReader(myFile);
        BufferedReader bufferedReader1 = new BufferedReader(fileReader1);
        while ((line = bufferedReader1.readLine()) != null) {
            String[] splittedValues = line.split(";");
            String subDepartmentString = splittedValues[1];
            String parentDepartment = "";
            if (splittedValues.length > 2) {
                parentDepartment = splittedValues[2];
                //now we add the SubDepartments
                for (Department dep : departments) {
                    if (dep.departmentName.equalsIgnoreCase(parentDepartment)) {
                        Department subDepartment = searchDepartment(subDepartmentString, departments);
                        dep.addSubDepartment(subDepartment);
                    }
                }
            }
        }

        printDepartments(departments);

        searchedDepartment = searchDepartmentOfEmployee("Frida Haudrauf", employees);
        destinationDepartment = findDepartmentInList("Vertrieb Europa", departments);
        searchedDepartment.switchDepartment("Frida Haudrauf", destinationDepartment );

        printDepartments(departments);

    }

    private static void printDepartments(ArrayList<Department> departmentList) {
        String myOutput;
        String firstLineOutput;
        myOutput = "";
        String myTabs = "\t";
        Department department = searchDepartment("Vorstand", departmentList);
        firstLineOutput = department.departmentName + " " + department.employees.get(0).name + "\n";
        myOutput = firstLineOutput + department.printDepartment(myOutput, myTabs);
        System.out.print(myOutput);
    }
    static Department searchDepartment(String departmentName, ArrayList<Department> departments) {
        Department searchedDepartment = null;
        for (Department dep : departments) {
            if (dep.departmentName.equalsIgnoreCase(departmentName)) {
                searchedDepartment = dep;
                break;
            }
        }
        return searchedDepartment;
    }


    static Department findDepartmentInList(String departmentNameString, ArrayList<Department> departmentArrayList) {
        Department destination = null;
        int index = 0;
        for (Department department : departmentArrayList){
            if(department.departmentName.equalsIgnoreCase(departmentNameString)){
                index = departmentArrayList.indexOf(department);
            }
        }
        destination = departmentArrayList.get(index);

        return destination;
    }

    static Department searchDepartmentOfEmployee(String name, ArrayList<Employee> employeeList) {
        Department department = null;
        for (Employee employee : employeeList) {
            if (employee.name.equalsIgnoreCase(name)) {
                department = employee.getDepartment();
                break;
            }

        }
        return department;
    }

}