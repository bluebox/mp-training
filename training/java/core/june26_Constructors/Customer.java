package june26_Constructors;

public class Customer {
	 String name;
	 float credit_limit;
	 String email;
	 Customer(String name,float credit,String emailAdd){
		 this.name=name;
		 credit_limit=credit;
		 email=emailAdd;
	 }
	 Customer(){
		this("greshma",12000.0f,"abc@gmail.com");
		 
	 }
	 
	 Customer(String name,String emailadd){
		 this(name,1200098,emailadd);
	 }
	 
	 public String getter() {
		 return ("Customer details:\n"+"name : "+name+"credit limit : "+credit_limit+"emailid : "+email);
	 }
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Customer c1=new Customer("arun",123000,"arun@gmail.com");
		System.out.println(c1.getter());
		Customer c2=new Customer();
		System.out.println(c2.getter());
		Customer c3=new Customer("janu","janu@gmail.com");
		System.out.println(c3.getter());
		
		
	}

}
