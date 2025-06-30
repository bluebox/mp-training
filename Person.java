
abstract class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public abstract void showDetails();

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
