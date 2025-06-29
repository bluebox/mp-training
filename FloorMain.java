package corejavaday_Three;

public class FloorMain {
    public static void main(String[] args) {
        Carpet carpet=new Carpet(3.5);
        Floor floor=new Floor(2.75,4.0);
        FloorCalculator cal=new FloorCalculator(floor,carpet);
        System.out.println("total="+cal.getTotalArea());
        carpet =new Carpet(1.5);
        floor=new Floor(5.4,4.5);
        cal=new FloorCalculator(floor,carpet);
        System.out.println("total="+cal.getTotalArea());
    }
}
