package June26;

public class BankMain {
	public static void main(String[] args) {
		
		Bank ram = new Bank(101, 500, "Ram", "ram@mail.com", "123456789");
		Bank abc = new Bank(107, 100, "ABC", "abc@mail.com", "987654321");
		
		System.out.println(abc);
		System.out.println(abc.getAccountNumber());
		System.out.println(abc.getAccountBalance());
		System.out.println(abc.getName());
		System.out.println(abc.getEmail());
		System.out.println(abc.getPhoneNumber());
		
		System.out.println(ram);
		ram.deposit(100);
		System.out.println(ram);
		ram.withdraw(200);
		System.out.println(ram);
		ram.withdraw(500);
		System.out.println(ram);
	}
}
