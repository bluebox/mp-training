import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        smartk kitchen = new smartk();

        kitchen.addWater();
        kitchen.pourMilk();
        kitchen.loadDishwasher();
        kitchen.doKitchenWork();
        System.out.println("cofee:");
        boolean c=sc.nextBoolean();
        System.out.println("fridge:");
        boolean f=sc.nextBoolean();

        System.out.println("dishwasher:");
        boolean d=sc.nextBoolean();


        kitchen.setKitchenState(c, f, d);
        kitchen.doKitchenWork();
    }
}