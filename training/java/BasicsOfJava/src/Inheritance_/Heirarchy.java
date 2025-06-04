package Inheritance_;

class Employee1 {
 String name;
 int employeeID;

 public Employee1(String name, int employeeID) {
     this.name = name;
     this.employeeID = employeeID;
 }

 public double getSalary() {
     return 50000.0;
 }
}


class Manager1 extends Employee1 {
 String department;

 public Manager1(String name, int employeeID, String department) {
     super(name, employeeID);
     this.department = department;
 }

 public void manageTeam() {
     System.out.println("Managing team in " + department);
 }
}


class Developer extends Employee1 {
 String programmingLanguage;

 public Developer(String name, int employeeID, String programmingLanguage) {
     super(name, employeeID);
     this.programmingLanguage = programmingLanguage;
 }

 public void code() {
     System.out.println("Coding in " + programmingLanguage);
 }
}


class Tester extends Employee1 {
 String testingTools;

 public Tester(String name, int employeeID, String testingTools) {
     super(name, employeeID);
     this.testingTools = testingTools;
 }

 public void testCode() {
     System.out.println("Testing code with " + testingTools);
 }
}

public class Heirarchy {
 public static void main(String[] args) {
     Manager1 manager = new Manager1("John Doe", 101, "Sales");
     Developer developer = new Developer("Jane Smith", 102, "Java");
     Tester tester = new Tester("Peter Jones", 103, "Selenium");

     System.out.println("Manager salary: " + manager.getSalary());
     manager.manageTeam();

     System.out.println("Developer salary: " + developer.getSalary());
     developer.code();

     System.out.println("Tester salary: " + tester.getSalary());
     tester.testCode();
 }
}