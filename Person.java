package Default;

public abstract class Person {
    private String name;
    private int age;
//using private ,we can implement encapsulation cause it protects the data from unknown.
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }


    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public abstract void showDetails();
}
