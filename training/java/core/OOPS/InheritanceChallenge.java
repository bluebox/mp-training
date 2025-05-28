package OOPS;
class Worker{
    String name;
    String birthDate; //dd/mm/yyyy
    String endDate;

    Worker(String name , String birthDate , String endDate){
        this.name = name;
        this.birthDate=birthDate;
        this.endDate = endDate;
    }

    public int getAge(){
        int present_year = 2025;
        int birth_year = Integer.parseInt(birthDate.substring(6));
        return present_year - birth_year;
    }

    public double collectPay(){
        return 1000000.0;
    }

    public void terminate(String endDate){
        System.out.println("employee terminated before end date " + endDate);
    }
}

class Employee extends Worker{

   
    long employeeId;
    String hireDate;

    Employee(String name , String birthDate , String endDate){
        super(name , birthDate , endDate);
    }

    Employee(String name , String birthDate , String endDate , long employeeId , String hireDate){
        super(name , birthDate , endDate);
        this.employeeId = employeeId;
        this.hireDate = hireDate;

    }

    public double collectPay(){
        return 1000000.0;
    }
    

}

class SalariedEmployee extends Employee{


    double annualSalary;
    boolean isRetired;

    SalariedEmployee(String name , String birthDate , String endDate , long employeeId,String hireDate ,double annualSalary , boolean isRetired){
        super(name , birthDate, endDate , employeeId , hireDate);
        this.annualSalary = annualSalary;
        this.isRetired = isRetired;
    }



    public void retire(){
        String present_day = "01/01/2025";
        if (present_day.compareTo(endDate) > 0){
            System.out.println("Employee reitred");
        }
        else{
            System.out.println("Employee not retired");
        }
    }

    public double collectPay(){
        return annualSalary;
    }

}

class HourlyEmployee extends Employee{

    
    double hourlyPayRate;

    HourlyEmployee(String name , String birthDate , String endDate ,long employeeId,String hireDate ,  double hourlyPayRate){
        super(name , birthDate,endDate , employeeId , hireDate);
        this.hourlyPayRate = hourlyPayRate;
    }

    public double getDoublePay(){
        double amount = 0;

        int days = 1200;
        amount = days*24*hourlyPayRate;
        return amount;
    }

    public double collectPay(){
        return getDoublePay();
    }

}

public class InheritanceChallenge {
    public static void main(String[] args) {
        SalariedEmployee emp = new SalariedEmployee("naga", "16/11/2001", "01/01/2027",1,"01/01/2025", 1000000, false);
        HourlyEmployee emp2 = new HourlyEmployee("reddy","12/12/1997" ,"01/01/2026", 2 , "01/01/2025",100);

        System.out.println(emp.collectPay());
        System.out.println(emp2.collectPay());
        System.out.println(emp.getAge());
        System.out.println(emp2.getAge());
    }
}
