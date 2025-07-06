
// import java.lang.classfile.instruction.ThrowInstruction;

package abstraction;

public abstract class productforsale {
	 String type;
	 String description;
	 double price;
	 int qty;

	public productforsale(int qty){
		this.qty = qty;
	}
    public productforsale(String type, double price, String description) {
        this.type=type;
        this.price=price;
        this.description=description;
       
    }
    public double getprice(int qty){
        return qty*price;
    }
    public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public static void printpriceditem(int qty){
        System.out.println("the quantity is " +qty);
    }
    protected abstract void showdetails();
	

}
