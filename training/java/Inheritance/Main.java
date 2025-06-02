package Inheritance;

public class Main {
    public static void main(String[] args) {
        SalariedEmployee emp1=new SalariedEmployee("Ram","28/02/2003", 10126,"21/05/2025",600000,false);
        HourlyEmployee emp2=new HourlyEmployee("Ram","28/02/2003", 10126,"21/05/2025",2);
        System.out.println(""+emp1.getAge()+" "+emp1.collectPay());
    }
}
