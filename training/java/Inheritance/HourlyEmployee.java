package Inheritance;

public class HourlyEmployee extends Employee {
    public double hourlyPayRate;

     public HourlyEmployee(String name,String birthDate,long employeeId,String hireDate,long hourlyPayRate){
        super(name,birthDate,employeeId,hireDate);
        this.hourlyPayRate=hourlyPayRate;
     }
    public void getDoublePay(){
        this.hourlyPayRate=hourlyPayRate*2;
    }
}
