package Hamburger;

public class BaseHamburger extends Hamburger {

    public BaseHamburger() {
        super(1.50d);
    }

    public boolean leftAddictions(){
        if(getAddictions().size() >= 4){
            return false;
        } else {
            return true;
        }
    }
}
