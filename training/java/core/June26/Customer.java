package June26;

public class Customer {

	private String name;
	private int creditLimit;
	private String email;
	
	public Customer() {
		this("Sahithi","sahithi@mail.com");
	}
	
	public Customer(String name, String email) {
		this(name, 100, email);
	}
	
	public Customer(String name, int creditLimit, String email) {
		this.name = name;
		this.creditLimit = creditLimit;
		this.email = email;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getCreditLimit() {
		return creditLimit;
	}
	
	public void setCreditLimit(int creditLimit) {
		this.creditLimit = creditLimit;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "Customer [name=" + name + ", creditLimit=" + creditLimit + ", email=" + email + "]";
	}
}
