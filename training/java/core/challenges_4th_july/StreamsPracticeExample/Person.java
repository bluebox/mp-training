package challenges_4th_july.StreamsPracticeExample;


public class Person {
    private String name;
    private int age;
    private String department;

    public Person(String name, int age, String department) {
        this.name = name;
        this.age = age;
        this.department = department;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public String getDepartment() { return department; }

    @Override
    public String toString() {
        return "Person{" +
               "name='" + name + '\'' +
               ", age=" + age +
               ", department='" + department + '\'' +
               '}';
    }
}