package learn2;

public class PrinterMain {
	
	public static void main(String[] args) {
		
		Printer hp = new Printer(false,40);
		System.out.println(hp.addToner(20));
		hp.printPages(40);
		System.out.println(hp.printPages(20));
		
		Printer dell = new Printer(true,30);
//		dell.printPages(40);
		System.out.println(dell.printPages(2));
	}
}
