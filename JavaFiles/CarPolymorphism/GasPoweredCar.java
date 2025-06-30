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
            "Cylinders: " + cylinders + ", Avg Km/L: " + avgKmPerLitre);
    }
}
