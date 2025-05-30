package encapsulation;

public class Main {
 public static void main(String [] args) {
	 Printer print = new Printer();
	 print.addToner(2);
	 print.printPages(200, false);
	 System.out.println("Toner level is "+ print.getTonerLevel());
	 System.out.println("Pages Printed are  "+ print.getPagesPrinted());
	 System.out.println("duplex : "+ print.getDuplex());
 }
}
