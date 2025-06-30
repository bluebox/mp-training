public class GasCar extends Car {
    private double mileage;
    private int cylinderCount;

    public GasCar(String model, double mileage, int cylinderCount) {
        super(model);
        this.mileage = mileage;
        this.cylinderCount = cylinderCount;
    }

    @Override
    protected void runEngine() {
        System.out.println("Gas-powered engine with " + cylinderCount + " cylinders, mileage: " + mileage + " km/l");
    }
}