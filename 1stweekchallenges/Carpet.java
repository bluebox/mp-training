public class Carpet {
    private double cost;
    public Carpet(double cost){
        this.cost=cost;
        if(this.cost<0)this.cost=0;
    }
    public double getCost() {
        return cost;
    }

}
