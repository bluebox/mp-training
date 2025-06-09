package encapsulation;

public class Printer {
	private int tonerLevel;
	private int pagesPrinted;
	private boolean duplex;
	
	Printer(int tonerLevel,boolean duplex){
		this.tonerLevel=tonerLevel;
		this.duplex=duplex;
	}
	
	public int addToner(int tonerAmount) {
		if (tonerLevel+tonerAmount>100) {
			return -1;
		}
		else {
			tonerLevel+=tonerLevel;
			return tonerLevel;
		}
	}
	
	public int printPages(int pages) {
		
	}
}
