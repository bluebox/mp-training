package training.java.core.CHALLENGES;

public class BurgerChallenge {
    public static void main(String[] args) {
        // Order o1= new Order();
        // o1.printBill();
        Burger b1= new Burger("regular",3);
        Drink d1= new Drink("small",2);
        SideItem item1= new SideItem("frankiee",3);
        
        Order o2=new Order(b1,d1,item1);
        o2.printBill();

    }
}

class Order{
    private Burger burger;
    private Drink drink;
    private SideItem item;
    private int totalBill;
    public Order() {
        this.burger = new Burger();
        this.drink = new Drink();
        this.item = new SideItem();
    }
    
    public Order(Burger burger, Drink drink, SideItem item) {
        this.burger = burger;
        this.drink = drink;
        this.item = item;
    }

    public void printBill(){
        
        if(burger.getCost()!=0){
            System.out.println("Burger : "+burger.getType() +" \nQuantity : "+burger.getQuantity()+" \nCost :"+ burger.getCost());
            System.out.println();
            totalBill+=burger.getCost();
        }
        if(drink.getCost()!=0){
            System.out.println("Drink : "+drink.getSize() +" \nQuantity : "+drink.getQuantity()+" \nCost :"+ drink.getCost());
            System.out.println();
            totalBill+=drink.getCost();
        }
        if(item.getCost()!=0){
            System.out.println("Item : "+item.getItem() +" \nQuantity : "+item.getQuantity()+" \nCost :"+ item.getCost());
            System.out.println();
            totalBill+=item.getCost();
        }
        System.out.println("You need to PAY : "+totalBill);
        System.out.println("\n-------THANK YOU ! VISIT AGAIN-------\n");
        
    }
    
}
class Burger{
    private String type;
    private int quantity;
    private int cost;
    public Burger(){
        type="regular";
        quantity=1;
        cost=150;

    }
    
    public Burger(String type, int quantity) {
        this.type = type;
        this.quantity = quantity;
         
        switch(type){
            case "small" : this.cost=100*quantity;
                            break;
            case "regular" : this.cost=150*quantity;
                            break;
            case "family" : this.cost=200*quantity;
                            break;
            default: cost=0;
        }
    }

    public String getType() {
        return type;
    }
    public int getQuantity() {
        return quantity;
    }
    public int getCost() {
        return cost;
    }
    public Burger(String type, int quantity, int cost) {
        this.type = type;
        this.quantity = quantity;
        this.cost = cost;
    }
    
}
class Drink{
    private String size;
    private int quantity;
    private int cost;
    public Drink() {
        size="small";
        quantity=1;
        cost=20;
    }
    
    public String getSize() {
        return size;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getCost() {
        return cost;
    }

    public Drink(String size,int quantity){
        this.size=size;
        this.quantity=quantity;
        switch (size) {
            case "small": cost=20*quantity;                
                break;
            case "medium": cost=50*quantity;                
                break;
            case "large": cost=80*quantity;                
                break;
        
            default:
                break;
        }
    }
    
}
class SideItem{
    private String item;
    private int quantity;
    private int cost;
    public SideItem() {
        item="fries";
        quantity=1;
        cost=100;
    }
    
    public String getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getCost() {
        return cost;
    }

    public SideItem(String item,int quantity){
        this.item=item;
        this.quantity=quantity;
        switch (item) {
            case "fries": cost=100*quantity;                
                break;
            case "chicken65": cost=120*quantity;                
                break;
            case "frankiee": cost=130*quantity;                
                break;
        
            default:
                break;
        }
    }
}