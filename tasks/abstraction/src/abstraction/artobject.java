package abstraction;


class artobject extends productforsale {
    

    public artobject(String type,double price,String description){
        super(type,price,description);
    } 
    
    @Override
    public void showdetails() {
        System.out.println("Type: " + this.type);
        System.out.println("Price: " + this.price);
        System.out.println("Description: " + this.description);
    }
    
}
