
public class constructorChallenge {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Customer c1=new Customer("chand","dcg@gmail.com");;
		System.out.println(c1.getName());
	}

}
class Customer
{
	private String name;
	private double creditlimit;
	private String email;
	public Customer(String name,double creditlimit,String email)
	{
		this.name=name;
		this.creditlimit=creditlimit;
		this.email=email;
	}
	public Customer()
	{
		this("sreeja",1000,"abc@gmail.com");
	}
	public Customer(String name,String email)
	{
		this(name,500,email);
	}
	public String getName() {
		return name;
	}
	public double getCreditlimit() {
		return creditlimit;
	}
	public String getEmail() {
		return email;
	}	
	}

	

