public class Salt extends ProductForSale {
    public Salt(String type,double price,String description){
        super(type, price, description);
    }

    public void showDetails(){
        System.out.println("salt "+this.getType()+" description"+this.getDescription()+"price"+this.getPrice());

    }
}
