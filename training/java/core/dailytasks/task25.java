public class task25 {
    private String name;
    private int age;
    private String email;

    // Constructor 1: All fields
    public task25(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    // Constructor 2: No-args, calls other constructor
    public task25() {
        this("Default Name", 0, "default@example.com");
    }

    // Constructor 3: Name and email, calls other constructor
    public task25(String name, String email) {
        this(name, 0, email);
    }

    // Getters (optional, but good practice)
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public static void main(String[] args) {
        task25 obj1 = new task25("John Doe", 30, "john.doe@example.com");
        task25 obj2 = new task25();
        task25 obj3 = new task25("Jane Doe", "jane.doe@example.com");

        System.out.println("Object 1: " + obj1.getName() + ", " + obj1.getAge() + ", " + obj1.getEmail());
        System.out.println("Object 2: " + obj2.getName() + ", " + obj2.getAge() + ", " + obj2.getEmail());
        System.out.println("Object 3: " + obj3.getName() + ", " + obj3.getAge() + ", " + obj3.getEmail());
    }
}