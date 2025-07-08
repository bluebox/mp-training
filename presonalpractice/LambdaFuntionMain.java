package dev.tulasidhar.presonalpractice;

public class LambdaFuntionMain {
	public static void main(String[] args) {
		//Product product = new Product("Bike");
		
		//print(product);
		//instead of creating a separate object of class that implements the Printable object , 
		//we are just giving the implementation of the single function of the interface
		
		Printable obj =  name -> System.out.println("hello " + name) ;
				
		
		obj.print("dasu");
		
		
	}

	public static void print(Printable obj) {
		obj.print("hi");
	}
}


