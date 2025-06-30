public class ElectricCar extends Car {
    private double rangePerCharge;
    private int batteryCapacity;

    public ElectricCar(String model, double rangePerCharge, int batteryCapacity) {
        super(model);
        this.rangePerCharge = rangePerCharge;
        this.batteryCapacity = batteryCapacity;
    }

    protected void runEngine() {
        System.out.println("Electric drive with " + batteryCapacity + " kWh battery, range: " + rangePerCharge + " km/charge");
    }
}