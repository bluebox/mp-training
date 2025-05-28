import java.util.*;

public class GroceryChallenge
{
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        cart.add(new Item("bread", 3.25));
        cart.add(new Item("milk", 2.50));
    
        // Uncomment these to test
        cart.add(new DiscountedItem("ice cream", 4.50, 1.50));
        cart.add(new DiscountedItem("apples", 1.35, 0.25));
    
        cart.printOrder();
    }
}

// DiscountedItem inherits from Item
class DiscountedItem extends Item
{
    private double discount;
    
    public DiscountedItem(String name, double price, double discount) {
        super(name, price);
        this.discount = discount;
    }
    
    public double getDiscount() {
        return discount;
    }
    
    public void setDiscount(double discount) {
        this.discount = discount;
    }
    
    public String toString() {
        return super.toString() + " (-" + super.valueToString(discount) + ")";
    }
}

class ShoppingCart
{
    private ArrayList<Item> order;
    private double total;
    private double internalDiscount;
    
    public ShoppingCart()
    {
        order = new ArrayList<Item>();
        total = 0.0;
        internalDiscount = 0.0;
    }
    
    public void add(Item i) {
        order.add(i);
        total += i.getPrice();
        if (i instanceof DiscountedItem)
        internalDiscount += ((DiscountedItem) i).getDiscount();
    }
    
    /** printOrder() will call toString() to print */
    public void printOrder() {
        System.out.println(this);
    }
    
    public String toString() {
        return discountToString();
    }
    
    public String discountToString() {
        return orderToString() + "\nSub-total: " + valueToString(total) + "\nDiscount: " + valueToString(internalDiscount) + "\nTotal: " + valueToString(total - internalDiscount);
    }
    
    private String valueToString(double value) {
        value = Math.rint(value * 100) / 100.0;
        String result = "" + Math.abs(value);
        if(result.indexOf(".") == result.length() - 2) {
            result += "0";
        }
        result = "$" + result;
        return result;
    }
    
    public String orderToString() {
        String build = "\nOrder Items:\n";
        for(int i = 0; i < order.size(); i++) {
            build += order.get(i).toString() + "\n";
        }
        return build;
    }
}

class Item
{
    private String name;
    private double price;
    
    public Item(String name, double price)
    {
        this.name = name;
        this.price = price;
    }
    
    public String getName()
    {
        return name;
    }
    
    public double getPrice()
    {
        return price;
    }
    
    public String toString()
    {
        return name + " " + valueToString(price);
    }
    
    public String valueToString(double value)
    {
        value = Math.rint(value * 100) / 100.0;
        String result = "" + Math.abs(value);
        if(result.indexOf(".") == result.length() - 2)
        {
            result += "0";
        }
        result = "$" + result;
        return result;
    }
}
/*
The following code contains a class called ShoppingCart that simulates a grocery store or an online store’s shopping cart. It has an ArrayList called order that you can use to add Items to the shopping cart. The Item class keeps track of the name and the price of each Item. If you run the code below, you will see that it adds 2 items to the cart and then prints out the total order. It may be easier to follow and change the code in this repl.it link. We encourage you to work in pairs.

In this challenge, you will add a new class called DiscountedItem that extends the Item class. The ArrayList of Items will still work since it can hold the subclasses of Items too! The ShoppingCart printOrder() method will work with Items and DiscountedItems but note that it has an if statement that treats DiscountedItems differently.

In the DiscountedItem subclass,

Add an instance variable for the discount amount.

Add constructors that call the super constructor Item.

Add get/set methods for discount. The get method is given below but you should modify it.

Add a toString() method that returns a string that includes a call to the super toString() method that will print out the price as well as the discount amount using the super.valueToString() method to format it. You could put the discount in parentheses with a minus sign in front of it like “(- $.50)”.

Uncomment the code in the main method to test adding DiscountedItems to the cart.

If you used repl.it or another IDE to complete this challenge, copy the code for DiscountedItem into the ActiveCode below so that it is saved for the next lesson.

*/ 