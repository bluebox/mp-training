public class Human {
    private String name;
    private int age;

    Human() {
        this.name = "default";
        this.age = 0;
    }

    Human(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String toString() {
        return this.getClass().getName() + " object, with name: " + name + " of age: " + age;
    }
}
