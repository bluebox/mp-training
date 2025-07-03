package Abstract;

public abstract class Product {
public String name;
public double price;

public Product(String name, double price) {
	this.name = name;
	this.price = price;
}
public abstract void displayDetails();
public String getName() {
	return name;
}

public double getPrice() {
	return price;
}




}
