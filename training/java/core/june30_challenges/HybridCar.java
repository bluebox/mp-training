package june30_collections;

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
        System.out.println(description + " is starting with " + cylinders + " cylinders and " + batterySize + " kWh battery.");
    }

    @Override
    public void drive() {
    	System.out.println(description+"is now driving");
    	runEngine();
    }
    @Override
    protected void runEngine() {
        System.out.println(description + " is running with fuel efficiency " + avgKmPerLitre + " km/l and battery " + batterySize + " kWh.");
    }
}
