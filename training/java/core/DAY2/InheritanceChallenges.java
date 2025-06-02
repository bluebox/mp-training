package training.java.core.DAY2;

public class InheritanceChallenges {
    public static void main(String[] args) {
        
    }
}
 class Worker {
    public String name;
    public String birthDate;
    public String endDate;
    public int getAge(){
        return Integer.parseInt(endDate)-Integer.parseInt(birthDate);
    }
    public double collectPay(){
        return getAge()*100;
    }
    public void terminate(String endDate){
        this.endDate=endDate;
    }
}

class Employee extends Worker {
    long employeeId;
    String hireDate;

}
class  SalariedEmployee extends Employee {
    public double annualSalary;
    public boolean isRetired;
    public boolean retire(){
        return isRetired;
    }
    
}