package corejava.july1_InnerClass;

class Meal {
    private Burger burger;

    public Meal(String name, double price) {
        this.burger = new Burger(name, price);
    }

    public void addToppings(String... toppingNames) {
        for (String topping : toppingNames) {
            burger.addTopping(topping);
        }
    }

    public void printMeal() {
        burger.printItem();
    }
}
