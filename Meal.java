import java.util.*;
public class Meal {
	ArrayList<Burger>al=new ArrayList();
	public Burger addBurger(String name, double price, String... toppings) {
        Burger b = new Burger(name, price);
        b.addToppings(toppings);
        al.add(b);
        return b;
    }

    public void printMeal() {
        for (Burger b :al) {
            b.print();
        }
    }

	class Burger
	{
		private String name;
		private double price;
		private List<Items>toppings=new ArrayList<>();
		Burger(String name,double price)
		{
			this.name=name;
			this.price=price;
		}
		public void addToppings(String...toppingNames)
		{
			for(var t:toppingNames)
			{
				switch(t.toLowerCase())
				{
				case "cheese","bacon"->toppings.add(new Items(t,20.0));
				case "lettuce","tomato","onion"->toppings.add(new Items(t,0));
				default->toppings.add(new Items(t,10.0));
				}
			}
				
		}
		public double getTotalPrice()
		{
			double sum=price;
			for(var t:toppings)
			{
				sum+=t.getPrice();
			}
			return sum;
		}
		 public void print() {
	            System.out.printf("Burger: %-10s | Base: $%.2f%n", name, price);
	            if (!toppings.isEmpty()) {
	                System.out.println("  Toppings:");
	                for (var t : toppings) {
	                    System.out.printf("    - %-8s : $%.2f%n", t.getName(), t.getPrice());
	                }
	            }
	            System.out.printf("  Total: $%.2f%n%n", getTotalPrice());
		 }
            
	}
}
