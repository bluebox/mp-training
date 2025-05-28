package learn2;

public class Printer {
	
	private int tonerLevel = 0;
	private int pagesPrinted = 0;
	private boolean duplex = false;
	
	public int getTonerLevel() {
		return tonerLevel;
	}
	
	public int getPagesPrinted() {
		return pagesPrinted;
	}
	
	public boolean getduplex() {
		
		return duplex;
	}
	
	public Printer(boolean duplex,int tonerLevel) {
		this.duplex = duplex;
		this.tonerLevel = tonerLevel;
	}
	
	public int addToner(int tonerAmount) {
		if (duplex) {
			tonerLevel += (2*tonerAmount);
		}
		else {
			tonerLevel = tonerAmount;
		}
		if(tonerLevel < 0) {
			return -1;
		}
		else if(tonerLevel > 100) {
			
			return -1;
		}
		return tonerLevel;
	}
	
	public int printPages(int pages) {
		if (duplex) {
			pagesPrinted += 2*pages;
			System.out.println("It is a Duplex Printer");
		}
		else {
			pagesPrinted += pages;
		}
		return pagesPrinted;
	}
}
