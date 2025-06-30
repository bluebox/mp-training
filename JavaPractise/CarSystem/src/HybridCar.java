public class HybridCar extends Car {
    private double fuelEfficiency;
    private int batterySize;
    private int engineCylinders;

    public HybridCar(String model, double fuelEfficiency, int batterySize, int engineCylinders) {
        super(model);
        this.fuelEfficiency = fuelEfficiency;
        this.batterySize = batterySize;
        this.engineCylinders = engineCylinders;
    }

    @Override
    protected void runEngine() {
        System.out.println("Hybrid system with " + engineCylinders + " cylinders and " +
                           batterySize + " kWh battery, fuel efficiency: " + fuelEfficiency + " km/l");
    }
}