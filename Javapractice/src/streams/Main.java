package streams;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    record Car(String type, String make, String model, Integer engineCapacity) {}

    public static void main(String[] args) {
        List<Car> cars = List.of(
            new Car("sedan", "BMW", "530", 1998),
            new Car("sedan", "Audi", "A5", 1998),
            new Car("sedan", "Mercedes", "E-Class", 2500),
            new Car("hatchback", "Skoda", "Octavia", 1600),
            new Car("hatchback", "Toyota", "TypeR", 1450)
        );

        List<Car> sedanCars = cars.stream()
            .filter(car -> car.type().equals("sedan"))
            .toList();

        List<String> carMakeList = cars.stream()
            .map(car -> car.make())
            .toList();

        List<String> carMakeModelList = cars.stream()
            .map(car -> car.make() + " " + car.model())
            .toList();
        
        Map<String,Map<String,Integer>> groupedcars= cars.stream().collect(
        		Collectors.groupingBy(Car::type,Collectors.toMap(Car::model, Car::engineCapacity)));
        		
        
        System.out.println(sedanCars);
        cars.parallelStream().sequential()
        .forEach(e -> {
            try {
                Thread.sleep(2000); 
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt(); 
            }
            System.out.println("hello this is forkjointhread - " + Thread.currentThread().getName());
        });
        System.out.println(carMakeList);
        System.out.println(carMakeModelList);
        System.out.println(groupedcars);
    }
}

