import java.util.Scanner;
public class consch {
    String name;
    int credit_limit;
    String email_address;

    
    public consch(String name, int credit_limit, String email_address) {
        this.name = name;
        this.credit_limit = credit_limit;
        this.email_address = email_address;
    }

    public consch() {
        this("Niha", 1000, "Niha@gmail.com");
    }

    public consch(String name, String email_address) {
        this(name, 0, email_address);
    }

    public String getName() {
        return name;
    }

    public int getCredit_limit() {
        return credit_limit;
    }

    public String getEmail_address() {
        return email_address;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter credit limit: ");
        int credit_limit = sc.nextInt();
        sc.nextLine(); 
        System.out.print("Enter email address: ");
        String email_address = sc.nextLine();

        consch obj = new consch(name, credit_limit, email_address);

        System.out.println("Name: " + obj.getName());
        System.out.println("Credit Limit: " + obj.getCredit_limit());
        System.out.println("Email Address: " + obj.getEmail_address());
    }
}