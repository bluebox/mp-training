package dev.tulasidhar.presonalpractice;


//functional interfaces should only have one abstract method
@FunctionalInterface
public interface Printable {
	
	
	//abstract interface
	void print(String name);
	
	
	
	static void print2() {
		//this is a static method , many static methods can be defined 
		// But only one abstract method should be defined in the interface for the interface to be a functional interface
	}
}
