public class Polymorphism {
    public static void main(String[] args) {
        Employee e = new Employee();
        Employee l = new Lecturer();
        e.designation();
        l.designation();
        l.designation(100000);
    }
}

class Employee {
    void designation() {
        System.out.println("I am an Employee!");
    }

    void designation(int salary) {
        System.out.println("I am an " + this.getClass().getName() + " with salary " + salary);
    }
}

class Lecturer extends Employee {
    void designation() {
        System.out.println("I am a Lecturer!");
    }
}
