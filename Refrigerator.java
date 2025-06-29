public class Refrigerator {
    private boolean hasWorkToDo;
    
    public void orderFood(){
        if(this.hasWorkToDo){
            System.out.println("ordering food");
        }
        
    }
   
   public boolean gethasWorkToDo(){
    return this.hasWorkToDo;
   }
   public void setHasWorkToDo(boolean hasWorkToDo) {
       this.hasWorkToDo = hasWorkToDo;
   }
    
}
