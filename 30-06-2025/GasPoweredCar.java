public class GasPoweredCar extends Car {
    private double avgKmPerLitre;
    private int cylinders;

    public GasPoweredCar(String description, double avgKmPerLitre, int cylinders) {
        super(description);
        this.avgKmPerLitre = avgKmPerLitre;
        this.cylinders = cylinders;
    }

    @Override
    public void startEngine() {
        System.out.println(getClass().getSimpleName() + " -> startEngine() with " + cylinders + " cylinders.");
    }

    @Override
    protected void runEngine() {
        System.out.println(getClass().getSimpleName() + " -> running on gasoline, efficiency: " + avgKmPerLitre + " km/l");
    }
}
