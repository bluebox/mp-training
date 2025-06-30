public class Main {
    public static void main(String[] args) {
        Car gasCar = new GasPoweredCar("Sedan", 15.5, 4);
        Car electricCar = new ElectricCar("Tesla", 400.0, 85);
        Car hybridCar = new HybridCar("Prius", 20.0, 50, 4);

        Car[] cars = {gasCar, electricCar, hybridCar};

        for (Car car : cars) {
            car.startEngine();
            car.drive();
            System.out.println();
        }
    }
}
