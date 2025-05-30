package POJOs;

public class Customers {

	private String customerName;
	private String customerNumber;
	private String customerMail;
	
	public Customers(String customerName,String customerNumber,String customerMail) {
		this.setCustomerName(customerName);
		this.setCustomerNumber(customerNumber);
		this.setCustomerMail(customerMail);
		
	}

	public String getCustomerMail() {
		return customerMail;
	}

	public void setCustomerMail(String customerMail) {
		this.customerMail = customerMail;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	@Override
	public String toString() {
		String details="Customer Name: "+customerName+"\n"+"Customer Number: "+customerNumber+"\n"+"Customer Mail: "+customerMail;
		return details;
		
	}
}
