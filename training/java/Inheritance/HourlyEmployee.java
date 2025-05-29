package Inheritance;

public class HourlyEmployee extends Employee{
    public double hourlyPayRate;

    public HourlyEmployee(String name, String birthDate, long employeeId, String hireDate,double hourlyPayRate) {
        super(name, birthDate, employeeId, hireDate);
        this.hourlyPayRate = hourlyPayRate;
    }

    public String display(){
        return "Hourly Employee { \nEmployee ID:"+employeeId +"\nName: "+name+"\nBirth Date: "+birthDate+"\nHire Date: "+hireDate+"\nHourlyPayRate :"+hourlyPayRate+"\n}";
    }
    public void getDoublePay(){
        hourlyPayRate = hourlyPayRate * 2;
    }
}
