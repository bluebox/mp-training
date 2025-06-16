import java.util.*;
class Details{
    private int id;
    private String name;
    private double salary;
    public int getId(){
       return id;
    }
    public String getName(){
       return name;
    }
    public double getSalary(){
       return salary;
    }
    public void setId(int id){
       this.id=id;
    }
    public void setName(String name){
       this.name=name;
    }
    public void setSalary(double salary){
       if(salary<0){
           System.out.println("Invalid salary");
       }else{
           this.salary=salary;
       }
    }
    public void displayDetails(){
       System.out.println("ID="+id+",Name="+name+",Salary="+salary);
    }
}
class Employee{
    public static void main(String[] args){
        Details details=new Details();
        details.setId(1);
        details.setName("Ram");
        details.setSalary(40000);
        details.displayDetails(); 
}
}
