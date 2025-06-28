public class coffeMaker {
    private boolean hasworktodo;
    public void sethasworktodo(boolean hasworktodo){
        this.hasworktodo=hasworktodo;

    }
    public void brewcofee(){
        if(hasworktodo){
            System.out.println("brewing cofee.....");
            hasworktodo=false;
        }
    }
    
}
