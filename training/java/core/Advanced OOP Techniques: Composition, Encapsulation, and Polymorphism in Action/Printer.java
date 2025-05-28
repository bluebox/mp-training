//Encapsulation Challenge
public class Printer {
 private int tonerLevel;
 private int pagesPrinted;
 private boolean duplex;
 
 public Printer(int tonerLevel, boolean duplex) {
	super();
	this.tonerLevel = tonerLevel;
	this.duplex = duplex;
	pagesPrinted=0;
}
public int addToner(int tonerAmount)
 {
	 if(tonerLevel>0 && tonerLevel<100)
	 {
		 if(tonerLevel+tonerAmount>100)
			 return -1;
		 tonerLevel+=tonerAmount;
		 return tonerLevel;
	 }
	 return -1;
 }
 public int printPages(int pages)
 {
	 if(this.duplex)
	 {
		 System.out.println("This is a duplex");
		 int p=(pages/2)+(pages%2);
		 pagesPrinted+=p;
		 return p;
	 }
	 pagesPrinted+=pages;
	 return pages;
 }
}
