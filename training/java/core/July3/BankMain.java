package July3;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class BankMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter number of customers to add: ");
		int n = sc.nextInt();
		sc.nextLine();

		List<BankCustomer> customers = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			System.out.println("\n--- Enter details for Customer " + (i + 1) + " ---");

			System.out.print("Enter customer name: ");
			String name = sc.nextLine();

			System.out.print("Enter customer id: ");
			int id = sc.nextInt();

			System.out.print("Enter customer balance: ");
			double balance = sc.nextDouble();

			sc.nextLine();

			AccountType accountType = null;
			while (accountType == null) {
				System.out.print("Enter customer account type (SAVINGS or CHECKINGS): ");
				String type = sc.nextLine().trim().toUpperCase();
				try {
					accountType = AccountType.valueOf(type);
				} catch (IllegalArgumentException e) {
					System.out.println("Invalid account type. Please enter SAVINGS or CHECKING.");
				}
			}

			BankCustomer customer = new BankCustomer(name, id);
			BankAccount account = new BankAccount(accountType, balance);
			customer.accounts.add(account);

			customers.add(customer);
		}

		System.out.println("\n--- Customer Details ---");
		for (BankCustomer customer : customers) {
			BankAccount account = customer.accounts.get(0);
			System.out.println("Customer Name: " + customer.getName());
			System.out.println("Customer ID: " + customer.getId());
			System.out.println("Account Type: " + account.getType());
			System.out.println("Account Balance: $" + account.getInitialAmount());
			System.out.println("-----------------------------");
		}

		sc.close();
	}
}
