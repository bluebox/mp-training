public class Main {
    public static void main(String[] args) {
        Car gasCar = new GasPoweredCar("Sports Car", 12.5, 6);
        Car electricCar = new ElectricCar("Tesla", 400, 85);
        Car hybridCar = new HybridCar("Hybrid Sedan", 20, 40, 4);

        gasCar.startEngine();
        gasCar.drive();
        gasCar.runEngine();

        System.out.println();

        electricCar.startEngine();
        electricCar.drive();
        electricCar.runEngine();

        System.out.println();

        hybridCar.startEngine();
        hybridCar.drive();
        hybridCar.runEngine();
    }
}
