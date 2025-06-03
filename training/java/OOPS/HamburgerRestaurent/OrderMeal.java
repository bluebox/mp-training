package HamburgerRestaurent;

import java.util.Scanner;

public class OrderMeal {
	private int toppingsCount=0;
	private double totalPrice;
	private boolean delux=false;
	private String toppingsList;
	Drink drink;
	SideItem sideItem;
	Burger burger;
	
	public OrderMeal()
	{
		burger= new Burger();
		drink= new Drink();
		sideItem = new SideItem();
		
	}
	public  void setToppings()
	{
		this.toppingsCount=3;
	}
	public OrderMeal(boolean delux,double totalPrice)
	{
		this.totalPrice=totalPrice;
		this.delux=delux;
		this.toppingsCount=5;
	}
	
	public void orderBurger()
	{
		String btype;
		double basePrice;
		System.out.println("1. classic burger 100rs\n"
				+ "2.BBQ burger 250rs\n"
				+ "3.veggie Burger 100rs");
		Scanner sc=new Scanner(System.in);
		int opt=sc.nextInt();
		
		burger=switch(opt)
		{
			case 1 -> new Burger("classic Burger",100);
			case 2 -> new Burger("BBQ Burger",250);
			case 3 -> new Burger("veggie Burger",150);
			default -> null;
		};
		
	}
	
	public void orderDrink(String type, String size)
	{
		String btype;
		double basePrice;
		System.out.println("1. classic burger 100rs\n"
				+ "2.BBQ burger 250rs\n"
				+ "3.veggie Burger 100rs");
		Scanner sc=new Scanner(System.in);
		int opt=sc.nextInt();
		
		drink=switch(opt)
		{
			case 1 -> new Drink("Thumbs up","mini");
			case 2 -> new Drink("BBQ Burger","medium");
			case 3 -> new Drink("veggie Burger","large");
			default -> null;
		};
	}

	public void orderSideItem(String type, double price)
	{
		sideItem=new SideItem(type,price);
	}
	public void addToppings(String topping)
	{
		this.toppingsList+=(topping+" ");
		if(this.toppingsCount<=0)
		{
			String opp="TOMATO";
			System.out.println();
		}
	}
	
	public double bill()
	{
		if(this.delux)
		{
			return this.totalPrice;
		}
		 
		this.totalPrice+= burger.bill()+drink.bill()+sideItem.bill();
		return totalPrice;
	}


	public static void main(String[] args) {
		
		Scanner sc= new Scanner(System.in);
		OrderMeal order;

		System.out.println("Welcome to Bobs Restaurent");
		System.out.println("Select a meal from the menu:");
		System.out.println("1.Default order((1)bruger,(1)Drink,(1)side Item, (3) any Toppings)\n"
				+ "2.Custom menu \n"
				+ "3.Delux Burger\n");
		int option=sc.nextInt();
		order=switch(option)
		{
			case 1 -> new OrderMeal();
			case 2 -> new OrderMeal();
			case 3 -> new OrderMeal(true, 100.00);
			default -> null;
		};
		OrderMeal.orders(option, order);
	}
	
	public static void orders(int option, OrderMeal obj)
	{
		if(option == 1)
		{
			obj.bill();
		}
		else
		{
			
		}
	}

}
class Drink{
	private String type= "coke";
	private String size = "mini";
	private double price;
	 public Drink(){
		
		setPrice("mini");
	}
	
	public Drink(String type, String size) {
		this.type = type;
		this.size = size;
		this.setPrice(size);
	}
	private void setPrice(String size )
	{
		if(size=="mini")
		{
			this.price=20.00;
		}
		else if(size == "medium")
		{
			this.price=45.00;
		}
		else
		{
			this.price=99.00;
		}
	}
	public double bill()
	{
		System.out.println("Drink ordered: "+this.type+"\n the size of the Drink: "+this.size
				+"the amount of the Drink:"+this.price);
		return this.price;
		
	}
	
	
}

class SideItem{
	private String type = "fries";
	private double price = 15.00;
	
	public SideItem() {
		
	}
	public SideItem(String type, double price) {
		this.type = type;
		this.price = price;
	}
	
	public double bill()
	{
		System.out.println("Side Item ordered: "+this.type+"\n the amount of the Side Item:"+this.price);
		return this.price;
		
	}
}

class Burger
{
	private String type="normal";
	private double Price = 30.0;
	
	public Burger() {

	}
	
	public Burger(String type, double basePrice) {
		this.type = type;
		this.Price = basePrice;
	}
	
	public double bill()
	{
		System.out.println("Drink ordered: "+this.type+"the amount of the Drink:"+this.Price);
		return this.Price;
		
	}
	
}



enum Toppings{
	BACON(2.23),
	CHEESE(3.4),
	LETTUCE(2.5),
	TOMATO(1.8),
	ONION( 3.2),
	PICKLES(1.6);
;
	double price;
	Toppings(double price)
	{
		this.price=price;
	}
}
