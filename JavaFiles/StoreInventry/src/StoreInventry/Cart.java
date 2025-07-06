package StoreInventry;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Cart {
	private String id;
    private List<Product> products = new ArrayList<>();
    private Date date;
    private String type;
    
	public Cart(String id,String type) {
		this.id = id;
		this.date = new Date();
		this.type = type;
	}

	public Date getDate() {
		return date;
	}

	

	public String getType() {
		return type;
	}

	
	public void addItem(Product p) {
        products.add(p);
    }

    public void removeItem(Product p) {
        products.remove(p);
    }

    public void printSalesSlip() {
        System.out.println("Sales Slip for Cart: " + id);
        for (Product p : products) {
            System.out.println("- " + p.getName() + " (" + p.getSku() + ")");
        }
    }
    
	

}
