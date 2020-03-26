package com.company;

import java.util.Arrays;

public class Departments {

    String departmentName;
    Departments subDepartment;
    int departmentcounter = 0;
    Departments[] departmentsArray = new Departments[10];
    String nameOfLeader;
    Employees[] employeeArray = new Employees[20];
    int employeeCounter = 0;


    //Constructor

    public Departments(String departmentName) {
        this.departmentName = departmentName;

    }

    public void addSubDepartment(Departments subDepartment) {
        for(Departments departments : departmentsArray) {
            if(departments != null) {
                if(subDepartment.departmentName.equalsIgnoreCase(departments.departmentName)){

                }
                this.subDepartment = subDepartment;
                departmentsArray[departmentcounter] = subDepartment;
                departmentcounter++;
            }
        }

    }

    public String printDepartment(String myOutput, String myTabs){
        String myPartlyOutput = myOutput;
        myTabs = myTabs + "\t";
        for (int i = 0; i < departmentsArray.length; i++) {
            if(departmentsArray[i] != null) {
                myPartlyOutput = myPartlyOutput + "\n" + myTabs + departmentsArray[i].departmentName;
                myPartlyOutput = myPartlyOutput + " (" + departmentsArray[i].employeeArray[0].name +")";
                myPartlyOutput = departmentsArray[i].printDepartment(myPartlyOutput, myTabs);
            }
        }

        return myPartlyOutput;
    }

    public void addEmployee(Employees employee){
        employeeArray[employeeCounter] = employee;
        employeeCounter++;
    }

    public void switchDepartment(Employees personWantsToSwitch, Departments switchDestination){
        for (int i = 0; i < employeeArray.length; i ++){
            if (employeeArray[i] != null) {
                if (employeeArray[i].equals(personWantsToSwitch)) {
                    employeeArray[i] = null;
                    employeeCounter--;
                    // search for a free place in the other employeeArray to switch
                    for (int j = 0; j < switchDestination.employeeArray.length; j++) {
                        if (switchDestination.employeeArray[j] == null) {
                            switchDestination.employeeArray[j] = personWantsToSwitch;
                            switchDestination.employeeCounter++;
                            break;
                        }
                    }
                    break;
                }
                else {
                    System.out.println("Switching Departments is not possible.");
                    break;
                }
            }
        }
    }
    
    public int getEmployeeCounter() {
        return employeeCounter;
    }




}

