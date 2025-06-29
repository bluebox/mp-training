public class CoffeeMaker {
    private boolean hasWorkToDo;

    public void brewCoffee(){
        if(this.hasWorkToDo)System.out.println("preparing coffee");

    }
    public boolean gethasWorkToDo(){
        return this.hasWorkToDo;
    }
    public void setHasWorkToDo(boolean hasWorkToDo) {
        this.hasWorkToDo = hasWorkToDo;
    }
}
