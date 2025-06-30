package challenges_30th_june.CarClassChallenge;

public class ElectricCar extends Car {
    private double avgKmPerCharge;
    private int batterySize;

    public ElectricCar(String description, double avgKmPerCharge, int batterySize) {
        super(description);
        this.avgKmPerCharge = avgKmPerCharge;
        this.batterySize = batterySize;
    }

    @Override
    public void startEngine() {
        System.out.println("ElectricCar -> startEngine()");
    }

    @Override
    public void drive() {
        System.out.println("ElectricCar -> drive(), Avg KM/Charge: " + avgKmPerCharge);
        runEngine();
    }

    @Override
    protected void runEngine() {
        System.out.println("ElectricCar -> runEngine()");
    }
}

