package Inheritance;

public class Emp {
    public static void main(String[] args) {
        Employee emp1= new Employee("Rakeshram","28/02/2004",212119,"21/05/2025");
        SalariedEmployee sEmp1=new SalariedEmployee("Shyam", "05/10/2003",432432,"03/06/2025",876543, false);
        HourlyEmployee hEmp1=new HourlyEmployee("Rahul", "15/08/2001",325321,"12/05/2022",700);
        System.out.println(emp1.display());
        System.out.println(sEmp1.display());
        System.out.println(hEmp1.display());
        System.out.println(emp1.getAge());
        System.out.println(sEmp1.getAge());
        System.out.println(hEmp1.getAge());
    }
}
