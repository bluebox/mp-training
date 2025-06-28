public class refrigerator {
    private boolean hasworktodo;
    public void sethasworktodo(boolean hasworktodo){
        this.hasworktodo=hasworktodo;
    }
    public void orderfood(){
        if(hasworktodo){
            System.out.println("ordering food.....");
            hasworktodo=false;
        }

    }


    
}
