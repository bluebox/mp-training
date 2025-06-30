public class HybridCar extends Car {
    private double avgKmPerLitre;
    private int batterySize;
    private int cylinders;

    public HybridCar(String description, double avgKmPerLitre, int batterySize, int cylinders) {
        super(description);
        this.avgKmPerLitre = avgKmPerLitre;
        this.batterySize = batterySize;
        this.cylinders = cylinders;
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
            "Cylinders: " + cylinders + ", Battery Size: " + batterySize +
            ", Avg Km/L: " + avgKmPerLitre);
    }
}
