import java.util.Scanner; 

public class UserInputExample {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = scanner.nextLine(); 
        System.out.println("Hello, " + name + "!");

       
        System.out.print("Enter your age: ");
        int age = scanner.nextInt(); 
        System.out.println("You are " + age + " years old.");

        
        scanner.nextLine();

       
        System.out.print("Enter your height in meters: ");
        double height = scanner.nextDouble(); 
        System.out.println("Your height is " + height + " meters.");

        
        scanner.nextLine();

        System.out.print("Are you a student? (true/false): ");
        boolean isStudent = scanner.nextBoolean(); 
        System.out.println("Student status: " + isStudent);

        
        scanner.close();
    }
}