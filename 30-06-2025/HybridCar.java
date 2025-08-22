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
        System.out.println(getClass().getSimpleName() + " -> startEngine() using hybrid system.");
    }

    @Override
    protected void runEngine() {
        System.out.println(getClass().getSimpleName() + " -> running on hybrid mode: " +
                batterySize + " kWh battery and " + cylinders + " cylinders.");
    }
}
