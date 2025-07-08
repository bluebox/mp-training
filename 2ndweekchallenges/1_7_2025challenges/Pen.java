public class Pen extends ProductForSale {
    public Pen(String type,double price,String description){
        super(type, price, description);
    }
    public void showDetails(){
                System.out.println("Pen "+this.getType()+" description"+this.getDescription()+"price"+this.getPrice());

    }
}
