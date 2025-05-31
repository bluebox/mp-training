package OOPS;
class Car{

    String description;



    public void startEngine(){
        System.out.println( " car engine started by "+ this.getClass().getSimpleName());
    }

    public void drive(){
        System.out.println(this.getClass()+ " driving..");
    }

    protected void runEngine(){
        System.out.println(this.getClass()+ " engine running");
    }

}

class GasPoweredCar extends Car{
    double avgKmPerLitre;
    int cylinder;

    GasPoweredCar(double avgKmPerLitre , int cylinder){
        this.avgKmPerLitre =avgKmPerLitre;
        this.cylinder = cylinder;
    }
}

class ElectricCar extends Car{
    double avgKmPerCharge;
    int batterySize;

    ElectricCar(double avgKmPerCharge , int batterySize){
        this.avgKmPerCharge = avgKmPerCharge;
        this.batterySize = batterySize;
    }


}

class HybridCar extends Car{
    int batterySize;
    int cylinder;
    double avgKmPerLitre;

    HybridCar(int batterySize , int cylinder , double avgKmPerLitre){
        this.batterySize = batterySize;
        this.cylinder = cylinder;
        this.avgKmPerLitre = avgKmPerLitre;
    }


}
public class CarChallenge {
    public static void main(String[] args) {
        GasPoweredCar gc = new GasPoweredCar(120, 2);
        ElectricCar ec = new ElectricCar(60, 100);
        HybridCar hc = new HybridCar(400, 40, 80);

        System.out.println(gc.avgKmPerLitre);
        gc.startEngine();
        gc.drive();

    }
}
