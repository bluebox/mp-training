package corejava.june26_Classes;

public class CustomerPoJo {
	private String name;
	private double creditLimit;
	private String email;
	
	public CustomerPoJo() {
		this("Deepika", 50_000.00,"abc@gmail.com");
	}	

	public CustomerPoJo(String name, String email) {
		this(name,1_00_000,email);
		
	}

	public CustomerPoJo(String name, double creditLimit, String email) {
		this.name = name;
		this.creditLimit = creditLimit;
		this.email = email;
	}
	
	public String getName() {
		return name;
	}
	public double getCreditLimit() {
		return creditLimit;
	}
	public String getEmail() {
		return email;
	}
	
}
