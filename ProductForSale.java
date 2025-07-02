package abst.lpa;

abstract class ProductForSale {
	private String type;
	private String descript;
	private double cost;
	public ProductForSale(String type,String descript,double cost) {
		this.cost=cost;
		this.descript=descript;
		this.type=type;
	}
	
	public double getSalesPrice(int quantity) {
		return cost*quantity;
	}
	
	public void printPricedItem(int qty) {
		System.out.printf("%2d at qty $ %7.2f each,%-15s ,%-35s %n", qty,cost,type,descript);
	}
	
	abstract void showDetails();
	
	public String getType() { 
		return type; 
	}
    public double getCost() { 
    	return cost; 	
    }
    public String getDescript() { 
    	return descript; 
    }
}

