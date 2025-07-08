public class Calculaor {
    private Floor floor;
    private Carpet carpet;
    public Calculaor(Floor floor,Carpet carpet){
        this.floor=floor;
        this.carpet=carpet;
    }
    public double getTotalCost(){
        return this.floor.getArea()*this.carpet.getCost();
    }
}
