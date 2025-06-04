package NestedClasses;
//inner class examples
public class CustomerAddress {
	private String name;
	private Address address;
	
	public CustomerAddress(String name,String city,int pincode) {
		this.name=name;
		this.address=new Address(city,pincode);
	}
	
	public void displayAddress() {
		System.out.println("Name: "+name);
		address.display();
	}
	public class Address{
		String city;
		int pincode;
		 public Address(String city,int pincode) {
			this.city=city;
			this.pincode=pincode;
		}
		 public void display() {
			 System.out.println("City: "+city+"\nPincode: "+pincode);
		 }
	}
	
	public static void main(String[] args) {
		CustomerAddress customer=new CustomerAddress("Raju","hyderabad",12345);
		customer.displayAddress();
	}
}
