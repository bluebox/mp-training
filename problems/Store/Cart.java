package Store;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

enum type{
	Physical,
	Virtual;
}

public class Cart {
      private int id;
      List<Product> products;
      type typ;
      LocalDate Date;
      
      
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public type getTyp() {
		return typ;
	}
	public void setTyp(type typ) {
		this.typ = typ;
	}
	public LocalDate getDate() {
		return Date;
	}
	public void setDate(LocalDate date) {
		Date = date;
	}
      
    public void addItem(Product product) {
    	products.add(product);
    	System.out.println("Product added to cart");
    }
    
    public Product removeItem(Product product) {
    	Iterator<Product> iterator=products.listIterator();
    	Product current=null;
    	while(iterator.hasNext()) {
    		current=iterator.next();
    		if(current.name.equals(product.name) && current.sku==product.sku && current.manufacturer.equals(product.manufacturer) && 
    				current.category.equals(product.category)) {
    			iterator.remove();
    		}
    	}
    	return current;
    	
    }
    
    public void printSalesSlip() {
    	for(Product prod:products) {
    		System.out.println("The cart sales slip: ");
    		System.out.println(prod.name+" Category : "+prod.category+" manufacturer : "+prod.manufacturer+" sku : "+prod.sku);
    	}
    }
      
}
