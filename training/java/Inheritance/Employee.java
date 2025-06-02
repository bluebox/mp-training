package Inheritance;

public class Employee extends Worker{
    public long employeeId;
    public String hireDate;

    public Employee(String name,String birthDate, long employeeId, String hireDate){
        super(name,birthDate);
        this.employeeId=employeeId;
        this.hireDate=hireDate;
    }
    public int getAge(){
        return super.getAge();
    }
}
