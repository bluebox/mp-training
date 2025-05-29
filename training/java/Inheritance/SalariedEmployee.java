package Inheritance;

public class SalariedEmployee extends Employee{
    public double annualSalary;
    public boolean isRetired;

    public SalariedEmployee(String name, String birthDate, long employeeId, String hireDate, double annualSalary, boolean isRetired) {
        super(name, birthDate,employeeId,hireDate);
        this.annualSalary = annualSalary;
        this.isRetired = isRetired;
    }

    public String display(){
        return "Salaried Employee { \nEmployee ID:"+employeeId +"\nName: "+name+"\nBirth Date: "+birthDate+"\nHire Date: "+hireDate+"\nAnnual Salary: "+annualSalary+"\nIs Retired: "+isRetired+"\n}";
    }
    public void retire(){
        isRetired = true;
    }
}
