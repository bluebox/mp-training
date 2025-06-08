package oops;
class Address{
	String city;
	String state;
	String country;
	public Address(String city, String state, String country) {
		super();
		this.city = city;
		this.state = state;
		this.country = country;
	}
	@Override
	public String toString() {
		return "Address [city=" + city + ", state=" + state + ", country=" + country + "]";
	}
}
class Employee{
	String name;
	int id;
	Address address;
	public Employee(String name, int id, Address address) {
		super();
		this.name = name;
		this.id = id;
		this.address = address;
	}
	public void display() {
		System.out.println(id+" "+name+" ");
		System.out.println(address.toString());
	}
	
}
public class Aggregation {

	public static void main(String[] args) {
		Address address=new Address("Hyderabad","Telangana","India");
		Employee emp=new Employee("Meghanadh",45,address);
		emp.display();
	}

}
