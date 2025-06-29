package corejavaday_Three;

public class FloorCalculator {
    Floor floor;
    Carpet carpet;
    public FloorCalculator(Floor floor,Carpet carpet){
        this.floor=floor;
        this.carpet=carpet;
    }
    public double getTotalArea(){
        return floor.getArea()*carpet.getCost();
    }
}
