public abstract class Listitem {
    protected Listitem  rightLink;
    protected Listitem leftLink;
    protected Object value;
    public Listitem(Object value){
        this.value=value;
    }
     abstract Listitem next();
     abstract Listitem setNext(Listitem li);
     abstract Listitem previous();
     abstract Listitem setPrevious(Listitem li);
     abstract int compareTo(Listitem li);
     
     public Object getValue(){
        return this.value;
     }
     public void setValue(Object value){
        this.value=value;
         
     }
     

    

}
