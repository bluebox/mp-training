package inheritance.medplus;

public class EncapsulationPrinter {
	public static void main(String[] args) {
		Printer printer = new Printer(23,true);
		System.out.println("Adding toner of 56 : "+printer.addToner(56));
		printer.printPages(98);
		
	}

}
class Printer{
	private int tonerLevel;
	private int pagesPrinted;
	private boolean duplex;
	public Printer(int tonerLevel, boolean duplex) {
		this.tonerLevel = tonerLevel;
		this.duplex = duplex;
		this.pagesPrinted=0;
	}
	public int addToner(int tonerAmount) {
		if ((tonerLevel+tonerAmount) >100) {
			tonerLevel = 100;
			return -1;
		}else {
			tonerLevel += tonerAmount;
			return tonerLevel;
		}
	}
	public void printPages(int pages) {
		if (duplex && (pages%2!=0)) {
			System.out.println("Total sheets printed : "+(pages/2)+1);
			System.out.println("it prints two sides");
			pagesPrinted = (pages/2)+1;
		}else if (duplex) {
			System.out.println("Total sheets printed : "+(pages/2));
			System.out.println("it prints two sides");
			pagesPrinted = (pages/2);
		}else {
			System.out.println("Total sheets printed : "+(pages));
			System.out.println("it prints one sides");
			pagesPrinted = (pages);
		}
	}
}

