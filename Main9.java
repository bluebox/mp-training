public class Main9 {
    public static void main(String[] args) {
        Printer myPrinter = new Printer(50);

        System.out.println("Initial Toner Level: " + myPrinter.getTonerLevel());

        int result = myPrinter.addToner(30);
        System.out.println("After adding 30: " + result); 

        result = myPrinter.addToner(25);
        System.out.println("After adding 25: " + result);

        result = myPrinter.addToner(-10);
        System.out.println("After adding -10: " + result); 

        result = myPrinter.addToner(20);
        System.out.println("After adding 20: " + result); 
    }
}
