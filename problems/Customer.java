package problems;

public class Customer {
   private String name;
   private double creditLimit;
   private String emailAddrress;
   private String contactNumber;
   
   public Customer(String name,double creditLimit,String email,String mobile) {
	   this.name=name;
	   this.creditLimit=creditLimit;
	   emailAddrress=email;
	   contactNumber=mobile;
   }
   
   public Customer(String name,double creditLimit,String mobile) {
	   this(name,creditLimit,null,mobile);
   }
   
   public Customer() {
	 this("default user",0,null,"default mobile");  
   }

		public String getName() {
			return name;
		}
		
		public double getCreditLimit() {
			return creditLimit;
		}
		
		public String getEmailAddrress() {
			return emailAddrress;
		}
		
		public String getContactNumber() {
			return contactNumber;
		}

 
   
   
}
