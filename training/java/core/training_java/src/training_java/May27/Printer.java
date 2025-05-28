package training_java.May27;

public class Printer {
	private int tonerLevel=75;
	private int pagesPrinted=0;
	private boolean duplex=true;
	public int addToner(int tonerAmount) {
		tonerLevel+=tonerAmount;
		if(tonerLevel > 100 || tonerLevel < 0) {
			return -1;
		}
		return tonerLevel;
	}
	public int printPages(int pages) {
		if(duplex) {
			System.out.println("It is a Duplex printer");
			pagesPrinted+=pages/2;
			System.out.println(" pages printed ="+ pagesPrinted);
			
		}
		else {
			pagesPrinted+=pages;
		}
		return pagesPrinted;
	}
public static void main(String[] args) {
	Printer p=new Printer();
	System.out.println("Toner Value: "+ p.addToner(23));
	p.printPages(45);
	
}
}
