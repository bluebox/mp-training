package abst.lpa;

public class Food extends ProductForSale {
    public Food(String type, String descript,double cost) {
        super(type, descript, cost);
    }

    @Override
    public void showDetails() {
        System.out.println("Food Product: " + getType());
        System.out.println("Ingredients: " + getDescript());
        System.out.println("Price: ₹" + getCost());
    }
}

