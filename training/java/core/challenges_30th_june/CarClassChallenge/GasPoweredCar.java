package challenges_30th_june.CarClassChallenge;

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
        System.out.println("GasPoweredCar -> startEngine()");
    }

    @Override
    public void drive() {
        System.out.println("GasPoweredCar -> drive(), Avg KM/L: " + avgKmPerLitre);
        runEngine();
    }

    @Override
    protected void runEngine() {
        System.out.println("GasPoweredCar -> runEngine()");
    }
}

