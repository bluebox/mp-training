package Day2_26_06;

public class AgeValidation {
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
    
    public static void main(String[] args) {
        AgeValidation person = new AgeValidation();

        person.setFirstName(",CJGBH");  
        person.setLastName("XK.sGVB");   
        person.setAge(10);
        System.out.println("Full Name: \"" + person.getFullName() + "\"");
        System.out.println("Is Teen: " + person.isTeen());

    }
}
