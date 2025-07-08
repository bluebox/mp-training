public class Scale extends ProductForSale{
    public Scale(String type,double price,String description){
        super(type, price, description);
    }
    public void showDetails(){
        System.out.println("Scale "+this.getType()+" description"+this.getDescription()+"price"+this.getPrice());
    }
}
