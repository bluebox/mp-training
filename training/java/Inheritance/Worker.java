package Inheritance;

public class Worker {
    public String name;
    public String birthDate;
    public String endDate;
    
    public Worker(String name,String birthDate){
        this.name=name;
        this.birthDate=birthDate;
    }
    public int getAge(){
        return Integer.parseInt(birthDate.substring(6))-2025;
    }

    public double collectPay(){
        return 43234.0d;
    }
    public void terminate(String endDate){
        this.endDate=  endDate;
        System.out.println("You have been Terminated");
    }
}
