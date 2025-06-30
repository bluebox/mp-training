package Project;

public class Main {
    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        Bank bank=new Bank("New Bank");
        boolean exit = false;

        while (!exit) {
            System.out.println("=== Banking Application Menu ===");
            System.out.println("1. Add Branch");
            System.out.println("2. Add Customer");
            System.out.println("3. Add Customer Transaction");
            System.out.println("4. List Customers");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            String input = scanner.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter New Branch Name: ");
                    String branchName = scanner.nextLine();
                    if (branchName.isEmpty()) {
                        System.out.println("Branch name cannot be empty. Please try again.");
                        continue;
                    }
                    bank.addBranch(branchName);
                    System.out.println("Branch added successfully.");
                    break;
                case 2:
                    System.out.print("Enter Existing Branch Name: ");
                    String existingBranchName = scanner.nextLine();
                    if (bank.findBranch(existingBranchName) == null) {
                        System.out.println("Branch not found. Please add the branch first.");
                        continue;
                    }
                    System.out.print("Enter Customer Name: ");
                    String customerName = scanner.nextLine();
                    System.out.print("Enter Initial Deposit Amount: ");
                    double initialDeposit;
                    try {
                        initialDeposit = Double.parseDouble(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid amount. Please enter a valid number.");
                        continue;
                    }
                    bank.addCustomer(existingBranchName, customerName, initialDeposit);
                    System.out.println("Customer added successfully.");
                    break;
                case 3:
                    System.out.print("Enter Existing Branch Name: ");
                    existingBranchName= scanner.nextLine();
                    if (bank.findBranch(existingBranchName) == null) {
                        System.out.println("Branch not found. Please add the branch first.");
                        continue;
                    }
                    System.out.print("Enter Customer Name: ");
                    String transCustomerName = scanner.nextLine();
                    System.out.print("Enter Transaction Amount: ");
                    double transAmount;
                    try {
                        transAmount = Double.parseDouble(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid amount. Please enter a valid number.");
                        continue;
                    }
                    bank.addCustomerTransaction(existingBranchName, transCustomerName, transAmount);
                    System.out.println("Transaction added successfully.");
                    break;
                case 4:
                    System.out.print("Enter Existing Branch Name: ");
                    existingBranchName = scanner.nextLine();
                    if (bank.findBranch(existingBranchName) == null) {
                        System.out.println("Branch not found. Please add the branch first.");
                        continue;
                    }
                    bank.listCustomers(existingBranchName, true);
                    break;
                case 5:
                    exit = true;
                    System.out.println("Thank you for using the Banking Application.");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
            System.out.println();
        }
        scanner.close();
    }
}
