public class DishWasher {
    private boolean hasWorkToDo;
    public void doDishes(){
        if(this.hasWorkToDo)
        System.out.println("the dishes are washing");

    }
    public boolean gethasWorkToDo(){
        return this.hasWorkToDo;
    }

}
