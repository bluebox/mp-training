package encapsulation;

public class Printer {
	private int tonerLevel ;
	private int pagesPrinted ;
	private boolean duplex ;
	public void printPages(int pagesPrinted, boolean duplex) {
		this.pagesPrinted=pagesPrinted;
		this.duplex=duplex;
	}
	public void addToner(int tonerLevel) {
		this.tonerLevel=tonerLevel;
	}
	public int getTonerLevel() {
		return tonerLevel;
	}
	public int getPagesPrinted() {
		return pagesPrinted;
	}
	public boolean getDuplex() {
		return duplex;
	}
}

