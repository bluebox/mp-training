public class Main {
    public static void main(String[] args) {
        Car electric=new ElectricCar();
        Car gaspowered=new GasPoweredCar();
        Car hybridcar=new HybridCar();

        electric.startEngine();
        electric.drive();
        gaspowered.startEngine();
        gaspowered.drive();
        hybridcar.startEngine();
        hybridcar.drive();
        System.out.println(electric);
        
    


    }
    
}
