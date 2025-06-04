package Hamburger;
public class HealthyBurger extends Hamburger {

    public HealthyBurger() {
        super(1.80d);
    }

    @Override
    public boolean leftAddictions() {
        if(getAddictions().size() >= 6){
            return false;
        } else {
            return true;
        }
    }
}
