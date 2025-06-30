public class Main {
    public static void main(String[] args) {
        Printer printer = new Printer(50, true);

        System.out.println("Current toner level: " + printer.addToner(30)); 
        System.out.println("Pages used: " + printer.printPages(7)); 
        System.out.println("Total pages printed: " + printer.getPagesPrinted());
    }
}
