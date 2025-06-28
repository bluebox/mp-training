public class dishwasher {
    private boolean hasworktodo;
    public void sethasworktodo(boolean hasworktodo){
        this.hasworktodo=hasworktodo;
    }
    public void dodishes(){
        if(hasworktodo){
            System.out.println("doing dishes.....");
            hasworktodo=false;
        }

    }
    
}
