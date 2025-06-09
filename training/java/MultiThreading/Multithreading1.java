package MultiThreading;

public class Multithreading1 {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        System.out.println(Thread.currentThread().threadId());
        System.out.println(Thread.currentThread().getPriority());
        Thread t1=new Thread();
        t1.start();
        Employee e1 = new Employee("Employee");
        e1.start();

        Manager m1 = new Manager("Manager");
        m1.start();
        System.out.println(Thread.activeCount());
    }
} 
class Employee extends Thread {
    public Employee(String name){
        super(name);
    }
    @Override
    public void run() {
        System.out.println("Employee thread :"+Thread.currentThread().getName()+" is running");
    }
}
class Manager extends Thread {
    public Manager(String name){
        super(name);
    }
    @Override
    public void run() {
        System.out.println("Manager thread :"+Thread.currentThread().getName()+" is running");
    }
}
