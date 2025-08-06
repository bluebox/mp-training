public class Method {
    public static void main(String[] args) {
        int salary = 100;
        double increamentRate = 0.2;
        int bonus = 1000;
        calcuteFinalSalary(salary, increamentRate, bonus);
        calcuteFinalSalary(10000, 0.3, 3000);
    }
    public static void calcuteFinalSalary(int salary, double increamentRate, int bonus){
        double finalSalary = salary +( salary * increamentRate ) + bonus ;
        System.out.println("final salary is " + finalSalary);
    }
}
