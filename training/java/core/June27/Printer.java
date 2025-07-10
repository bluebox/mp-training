package June27;

public class Printer {
	
	private int tonerLevel;
	private int pagesPrinted;
	private boolean duplex;
	
	public Printer(int tonerLevel, boolean duplex) {
		this.tonerLevel = (tonerLevel>-1 && tonerLevel<=100) ? tonerLevel:-1;
		this.duplex = duplex;
		pagesPrinted = 0;
	}
	
	public int getTonerLevel() {
		return tonerLevel;
	}
	
	public int getPagesPrinted() {
		return pagesPrinted;
	}
	
	public boolean isDuplex() {
		return duplex;
	}
	
	public void setTonerLevel(int tonerLevel) {
		this.tonerLevel = tonerLevel;
	}
	
	public void setPagesPrinted(int pagesPrinted) {
		this.pagesPrinted = pagesPrinted;
	}
	
	public void setDuplex(boolean duplex) {
		this.duplex = duplex;
	}
	
	public int addToner(int tonerAmount) {
		  if((tonerAmount+this.tonerLevel)<=100) {
			  this.tonerLevel=this.tonerLevel+tonerAmount;
			  return this.tonerLevel;
		  }else {
			  return -1;
		  }
	  }
	  
	  public int printPages(int pages) {
		  if(this.duplex) {
			  System.out.println("Printing in duplex mode");
			  this.pagesPrinted += pages/2;
		  }else {
			  this.pagesPrinted += pages;
		  }
		  
		  return pages;
	  }
}
