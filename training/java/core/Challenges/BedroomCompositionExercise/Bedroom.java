package training.java.core.Challenges.BedroomCompositionExercise;

public class Bedroom {
    String name;
    Wall wall1;
    Wall wall2;
    Wall wall3;
    Wall wall4;
    Ceiling ceiling;
    Bed bed;
    Lamp lamp;

    public Bedroom(String name, Wall wall1, Wall wall2, Wall wall3, Wall wall4, Ceiling ceiling, Bed bed, Lamp lamp){
        this.name = name;
        this.wall1 = wall1;
        this.wall2 = wall2;
        this.wall3 = wall3;
        this.wall4 = wall4;
        this.ceiling = ceiling;
        this.bed = bed;
        this.lamp = lamp;
    }

    public Lamp getLamp(){
        return lamp;
    }

    public void makeBed(){
        System.out.println("Bedroom > Making bed | ");
        bed.make();
    }

    // public static void main(String args[]){
    //     Wall wll1 = new Wall("West");
    //     Wall wll2 = new Wall("East");
    //     Wall wll3 = new Wall("South");
    //     Wall wll4 = new Wall("North");

    //     Ceiling ceil = new Ceiling(12, 55);
    //     Bed bd = new Bed("Modern", 4, 3, 2, 1);
    //     Lamp lmp = new Lamp("Classic", false, 75);

    //     Bedroom bdroom = new Bedroom("Surya Chandra Teja", wll1, wll2, wll3, wll4, ceil, bd, lmp);
    //     bdroom.makeBed();
    //     bdroom.getLamp().turnOn();
    // }
}
