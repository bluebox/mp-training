package Inheritance;

public class SalariedEmployee extends Employee{
    public double annualSalary;
    public boolean isRetired;

    public SalariedEmployee(String name,String birthDate,long employeeId,String hireDate,long annualSalary,boolean isRetired){
        super(name,birthDate,employeeId,hireDate);
        this.annualSalary = annualSalary;
        this.isRetired = isRetired;
    }

    public void retire(){
        this.isRetired = true;
    }
}
