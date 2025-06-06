package encapsulation;

public class Printer {
	private int tonerLevel;
	private int pagesPrinted;
	private boolean duplex;
	
	Printer(int tonerLevel,boolean duplex){
		this.tonerLevel=tonerLevel;
		this.duplex=duplex;
	}
	public void printValues() {
		System.out.print(tonerLevel);
	}
}
