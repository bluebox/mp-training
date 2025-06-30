class Person {
    private String firstName;
    private String lastName;
    private int age;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        if (age < 0 || age > 100) {
            this.age = 0;
        } else {
            this.age = age;
        }
    }

    public boolean isTeen() {
        return age > 12 && age < 20;
    }

    public String getFullName() {
        if (firstName.isEmpty() && lastName.isEmpty()) {
            return "";
        } else if (firstName.isEmpty()) {
            return lastName;
        } else if (lastName.isEmpty()) {
            return firstName;
        } else {
            return firstName + " " + lastName;
        }
    }
}

public class PersonClassAndAgeValidation {
    public static void main(String[] args) {
        Person person = new Person();
        person.setFirstName(""); 
        person.setLastName(""); 
        person.setAge(10);
        System.out.println("Full Name = " + person.getFullName());
        System.out.println("Teen = " + person.isTeen());

        person.setFirstName("John"); 
        person.setAge(18);
        System.out.println("Full Name = " + person.getFullName());
        System.out.println("Teen = " + person.isTeen());

        person.setLastName("Smith");
        System.out.println("Full Name = " + person.getFullName());
    }
}