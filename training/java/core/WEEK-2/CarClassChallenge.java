class Car{
    private String description;
    
    public Car(String description){
        this.description=description;
    }

    public Car(){
        this("Description not provided");
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description=description;
    }

    public void startEngine(){
        System.out.println("Engine started");
    }
    public void drive(){
        runEngine();
        System.out.println("Car is being driven");
    }
    protected void runEngine(){
        System.out.println("Engine Running");
    }
}

class GasPoweredCar extends Car{
    private double avgKmPerLitre;
    private int cylinders;

    public GasPoweredCar(double avgKmPerLitre, int cylinders, String description){
        super(description);
        this.avgKmPerLitre=avgKmPerLitre;
        this.cylinders=cylinders;
    }

    public GasPoweredCar(double avgKmPerLitre, int cylinders){
        super("Description not provided");
        this.avgKmPerLitre=avgKmPerLitre;
        this.cylinders=cylinders;
    }

    public double getAvgKmPerLitre(){
        return avgKmPerLitre;
    }
    public void setAvgKmPerLitre(double avgKmPerLitre){
        this.avgKmPerLitre=avgKmPerLitre;
    }

    public int getCylinders(){
        return cylinders;
    }
    public void setCylinders(int cylinders){
        this.cylinders=cylinders;
    }

    @Override
    public void startEngine(){
        System.out.println("Engine started with gas");
    }

    @Override
    public void drive(){
        runEngine();
        System.out.println("Car is being driven and causing pollution");
    }

    @Override
    protected void runEngine(){
        System.out.println("Engine Running by burning gas");
    }
}

class ElectricCar extends Car{
    private double avgKmPerLitre;
    private int batterySize;

    public ElectricCar(double avgKmPerLitre, int batterySize, String description){
        super(description);
        this.avgKmPerLitre=avgKmPerLitre;
        this.batterySize=batterySize;
    }

    public ElectricCar(double avgKmPerLitre, int batterySize){
        super("Description not provided");
        this.avgKmPerLitre=avgKmPerLitre;
        this.batterySize=batterySize;
    }

    public double getAvgKmPerLitre(){
        return avgKmPerLitre;
    }
    public void setAvgKmPerLitre(double avgKmPerLitre){
        this.avgKmPerLitre=avgKmPerLitre;
    }
    public int getBatterySize(){
        return batterySize;
    }
    public void setBatterySize(int batterySize){
        this.batterySize=batterySize;
    }

    @Override
    public void startEngine(){
        System.out.println("Engine started with Electricity");
    }

    @Override
    public void drive(){
        runEngine();
        System.out.println("Car is being driven without causing pollution");
    }

    @Override
    protected void runEngine(){
        System.out.println("Engine Running by using electricity");
    }
}

class HybridCar extends Car{
    private double avgKmPerLitre;
    private int cylinders;
    private int batterySize;

    public HybridCar(double avgKmPerLitre, int batterySize, int cylinders, String description){
        super(description);
        this.avgKmPerLitre=avgKmPerLitre;
        this.batterySize=batterySize;
        this.cylinders=cylinders;
    }

    public HybridCar(double avgKmPerLitre, int batterySize, int cylinders){
        super("Description not provided");
        this.avgKmPerLitre=avgKmPerLitre;
        this.batterySize=batterySize;
        this.cylinders=cylinders;
    }

    public double getAvgKmPerLitre(){
        return avgKmPerLitre;
    }
    public void setAvgKmPerLitre(double avgKmPerLitre){
        this.avgKmPerLitre=avgKmPerLitre;
    }
    public int getCylinders(){
        return cylinders;
    }
    public void setCylinders(int cylinders){
        this.cylinders=cylinders;
    }
    public int getBatterySize(){
        return batterySize;
    }
    public void setBatterySize(int batterySize){
        this.batterySize=batterySize;
    }

    @Override
    public void startEngine(){
        System.out.println("Engine can be started with gas as well as electricity");
    }

    @Override
    public void drive(){
        runEngine();
        System.out.println("Car is being driven and causing less pollution and also utilizing less gas");
    }

    @Override
    protected void runEngine(){
        System.out.println("Engine Running by burning gas and using electricity");
    }
}

public class CarClassChallenge {
    public static void main(String[] args) {
        Car car = new Car("Basic Car");
        car.startEngine();
        car.drive();

        Car gasCar = new GasPoweredCar(15.0, 4, "Gas Powered Car");
        gasCar.startEngine();
        gasCar.drive();

        Car electricCar = new ElectricCar(20.0, 75, "Electric Car");
        electricCar.startEngine();
        electricCar.drive();

        Car hybridCar = new HybridCar(18.0, 50, 4, "Hybrid Car");
        hybridCar.startEngine();
        hybridCar.drive();
    }
}