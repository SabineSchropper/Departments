package com.company;

public class Main {

    public static void main(String[] args) {

        String output = "";

        Employees person1 = new Employees("Thomas","Mustermann");
        Employees person2 = new Employees("Nina", "Anders");
        Employees person3 = new Employees("Tina","Marte");
        Employees person4 = new Employees("Max","Wilhelmer");

        Departments vorstand = new Departments("Vorstand", "Alfred Boss");
        Departments vertriebLeiter = new Departments("Vertrieb Leiter","Mustermann Max");
        Departments vertriebPrivatkunden = new Departments("Vertrieb Privatkunden","Musterfrau Angela");
        Departments vertriebFirmenkunden = new Departments("Vertrieb Firmenkunden","Muste Alfons");
        Departments einkaufLeiter = new Departments("Einkauf Leiter","Kufmann Alois");
        Departments einkaufMechanik = new Departments("Einkauf Mechanik","Gunz Herlinde");
        Departments einkaufKleinteile = new Departments("Einkauf Kleinteile","But Moritz");
        Departments einkaufGrossteile = new Departments("Einkauf Grossteile","Friedrich Hermann");
        Departments einkaufEuropa = new Departments("Einkauf Europa","Peter Hannelore");

        vorstand.addSubDepartment(vertriebLeiter);
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


    }
}
