package Hamburger;
public class Main {

    public static void main(String[] args){
        DeluxeHamburger hamburger = new DeluxeHamburger();
        hamburger.addCarrot();
        hamburger.addCheese();
        hamburger.addKetchup();
        hamburger.addCarrot();
        hamburger.addTomato();
        hamburger.addMustard();
        hamburger.addLettuce();
        hamburger.totalCost();
    }
}
