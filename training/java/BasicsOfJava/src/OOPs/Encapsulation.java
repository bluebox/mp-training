package OOPs;


class Printer {
 private int tonerLevel;
 private int pagesPrinted;
 private boolean duplex;
 
 
 public Printer(int tonerLevel, boolean duplex) {
	this.tonerLevel = tonerLevel;
	this.duplex = duplex;
}
public int addToner(int tonerAmount)
 {
	int res= (tonerAmount + this.tonerLevel) < 0 || (tonerAmount + this.tonerLevel) >100 ? -1:tonerAmount;
	if (res>0)
	{
		this.tonerLevel+=tonerAmount;
		return this.tonerLevel;
	}
	else
	{
		return res;
	}
	
 }
public int getPagesPrinted() {
	return pagesPrinted;
}
 public int printPages(int pages)
 {
	 
	 if(this.duplex)
	 {
		 int nPages=(pages%2) == 0? (pages/2):((pages/2) +1);
		 this.pagesPrinted+= nPages;
		 return nPages;
	 }
	 
	 this.pagesPrinted+=pages;
	 return pages;
 }
	
	
}
public class Encapsulation{
	public static void main(String[] args) {
		Printer printer= new Printer(70,true);
		System.out.println(printer.addToner(50));
		System.out.println(printer.printPages(30));

	}

}