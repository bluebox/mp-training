public abstract class ListItem{
    //protected Node rightLink;
    //protected Node leftLink;
    Object value;
    public ListItem(Object value){
        this.value=value;
    }
    private Object getValue(){
        return value;
    }
    private void setValue(Object value){
        this.value=value;
    }
    private void next(){}
    private void setNext(){}
    private void previous(){}
    private void setPrevious(){}
    private void compareTo(){}

}
