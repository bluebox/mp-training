package dev.tulasidhar.presonalpractice;

public class Product implements Printable{
	String name;
	public Product(String name) {
		this.name = name;
	}
	public void print(String name) {
		System.out.println(name);
		
	}
}
