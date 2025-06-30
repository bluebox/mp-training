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
        System.out.println(getClass().getSimpleName() + " -> startEngine()");
    }

    @Override
    public void drive() {
        System.out.println(getClass().getSimpleName() + " -> drive()");
        super.runEngine();
    }

    @Override
    protected void runEngine() {
        System.out.println(getClass().getSimpleName() + " -> runEngine(): " +
            "Battery Size: " + batterySize + ", Avg Km/Charge: " + avgKmPerCharge);
    }
}
