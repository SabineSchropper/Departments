package com.company;

import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        Employees[] employeesArray = new Employees[30];
        //ArrayList<Employees> employeeList = new ArrayList<>();
        Departments[] departmentsArray = new Departments[15];
        int employeeCounter = 0;
        int departmentCounter = 0;
        String parentDepartment = "";
        String myOutput = "";

        File myFile = new File("C:\\Users\\DCV\\Documents\\Abteilungen1.txt");
        FileReader fileReader = new FileReader(myFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = null;

        while ((line = bufferedReader.readLine()) != null) {
            String[] splittedValues = line.split(";");
            String name = splittedValues[0];
            String department = splittedValues[1];
            if (splittedValues.length > 2) {
                parentDepartment = splittedValues[2];
            }
            employeesArray[employeeCounter] = new Employees(name);
            departmentsArray[departmentCounter] = new Departments(department);
            //next Step is to add the employee to the department
            departmentsArray[departmentCounter].addEmployee(employeesArray[employeeCounter]);
            // run through departmentsArray and add subdepartment "department"
            if (splittedValues.length > 2) {
                for (Departments departments : departmentsArray) {
                    if (departments != null) {
                        if (departments.departmentName.equalsIgnoreCase(parentDepartment)) {
                            departments.addSubDepartment(departmentsArray[departmentCounter]);
                        }
                    }
                }
            }
            employeeCounter++;
            departmentCounter++;
        }


      /*  vorstand.addSubDepartment(vertriebLeiter);
        //vorstand.addSubDepartment(vertriebPrivatkunden);

        vertriebLeiter.addSubDepartment(vertriebPrivatkunden);
        vertriebLeiter.addSubDepartment(vertriebFirmenkunden);

        vorstand.addSubDepartment(einkaufLeiter);
        einkaufLeiter.addSubDepartment(einkaufMechanik);

        einkaufMechanik.addSubDepartment(einkaufKleinteile);
        einkaufMechanik.addSubDepartment(einkaufGrossteile);

        einkaufGrossteile.addSubDepartment(einkaufEuropa);

        output = "\t" + vorstand.departmentName + " " + vorstand.nameOfLeader + vorstand.printDepartment(output, "\t");
        System.out.println(output);

        vertriebPrivatkunden.addEmployee(person1);
        vertriebPrivatkunden.addEmployee(person2);

        System.out.println("Im "+vertriebPrivatkunden.departmentName+ " arbeiten "+vertriebPrivatkunden.getEmployeeCounter()+ " Personen.");

        vertriebPrivatkunden.switchDepartment(person1,einkaufGrossteile);

        System.out.println("Im Vertrieb Privatkunden arbeiten "+vertriebPrivatkunden.getEmployeeCounter()+ " Personen.");
        System.out.println("Im Einkauf für Großteile arbeiten "+einkaufGrossteile.getEmployeeCounter()+ " Personen.");

        einkaufEuropa.addEmployee(person4);

        einkaufEuropa.switchDepartment(person1, einkaufGrossteile);
        einkaufEuropa.switchDepartment(person4, einkaufGrossteile);

        System.out.println("Im Einkauf für Großteile arbeiten "+einkaufGrossteile.getEmployeeCounter()+ " Personen.");

       */
        String myFirstLine = "\t" + departmentsArray[1].departmentName + " " + departmentsArray[1].employeeArray[0].name;
        myOutput = "";
        String myTabs = "\t";
        myOutput = myFirstLine + departmentsArray[1].printDepartment(myOutput, myTabs);
        System.out.println(myOutput);


    }
}
