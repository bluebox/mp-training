public class Teacher extends Human {
    private String subject;
    private int salary;

    Teacher(String name, int age, String subject, int salary) {
        super(name, age);
        this.subject = subject;
        this.salary = salary;
    }

    public String toString() {
        return super.toString() + " teaching " + subject + " working with a salary of " + salary;
    }
}
