package com.company;

import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        ArrayList<Employee> employeeList = new ArrayList<>();
        ArrayList<Department> departmentList = new ArrayList<>();
        String parentDepartment = "";
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
            if (splittedValues.length > 2) {
                parentDepartment = splittedValues[2];
            }
            Employee employee = new Employee(name);
            employeeList.add(employee);
            Department department = new Department(departmentName, employee);
            employee.setDepartment(department);
            //if departmentName already exists we only add the employee, else we add the department to the list
            for (Department dep : departmentList) {
                if (dep.departmentName.equalsIgnoreCase(department.departmentName)) {
                    doesDepartmentAlreadyExists = true;
                }
            }
            if (doesDepartmentAlreadyExists) {
                department.addEmployee(employee);
            } else {
                departmentList.add(department);
            }

            // run through departmentsArrayList and add subdepartment "department"
            if (splittedValues.length > 2) {
                for (Department dep : departmentList) {
                    if (dep.departmentName.equalsIgnoreCase(parentDepartment)) {
                        dep.addSubDepartment(department);
                    }
                }
            }
        }

        printDepartments(departmentList);

        searchedDepartment = searchDepartmentOfEmployee("Frida Haudrauf", employeeList);
        destinationDepartment = findDepartmentInList("Vertrieb Europa", departmentList);
        searchedDepartment.switchDepartment("Frida Haudrauf", destinationDepartment );

        printDepartments(departmentList);

    }

    private static void printDepartments(ArrayList<Department> departmentList) {
        String myOutput;
        String firstLineOutput;
        myOutput = "";
        String myTabs = "\t";
        firstLineOutput = departmentList.get(0).departmentName + " " + departmentList.get(0).employeeArrayList.get(0).name + "\n";
        myOutput = firstLineOutput + departmentList.get(0).printDepartment(myOutput, myTabs);
        System.out.print(myOutput);
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