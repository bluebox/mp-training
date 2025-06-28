public class Operators {

    public static void main(String[] args) {

        System.out.println("=== Arithmetic Operators ===");
        int num1 = 12, num2 = 4;
        System.out.println("Initial num1: " + num1 + " num2: "+num2);
        System.out.println("Addition: " + (num1 + num2));
        System.out.println("Subtraction: " + (num1 - num2));
        System.out.println("Multiplication: " + (num1 * num2));
        System.out.println("Division: " + (num1 / num2));
        System.out.println("Remainder: " + (num1 % num2));

        System.out.println("\n=== Assignment Operators ===");
        int total = 10;
        System.out.println("Initial total: " + total);
        total += 5;
        System.out.println("total += 5 -> " + total);
        total -= 3;
        System.out.println("total -= 3 -> " + total);
        total *= 2;
        System.out.println("total *= 2 -> " + total);
        total /= 4;
        System.out.println("total /= 4 -> " + total);
        total %= 3;
        System.out.println("total %= 3 -> " + total);

        System.out.println("\n=== Relational Operators ===");
        int age = 20;
        System.out.println("Is age == 18? " + (age == 18));
        System.out.println("Is age != 18? " + (age != 18));
        System.out.println("Is age > 18? " + (age > 18));
        System.out.println("Is age < 18? " + (age < 18));
        System.out.println("Is age >= 18? " + (age >= 18));
        System.out.println("Is age <= 18? " + (age <= 18));

        System.out.println("\n=== Logical Operators ===");
        boolean hasLicense = true;
        boolean hasID = false;
        System.out.println("Can drive (hasLicense && hasID)? " + (hasLicense && hasID));
        System.out.println("Can enter (hasLicense || hasID)? " + (hasLicense || hasID));
        System.out.println("No license? " + (!hasLicense));

        System.out.println("\n=== Bitwise Operators ===");
        int a = 6, b = 3;
        System.out.println("a & b: " + (a & b));
        System.out.println("a | b: " + (a | b));
        System.out.println("a ^ b: " + (a ^ b));
        System.out.println("a << 1: " + (a << 1));
        System.out.println("a >> 1: " + (a >> 1));
    }
}
