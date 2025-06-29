package Day3;

public class Printer {
   int tonerLevel;
   int pagesPrinted;
   boolean duplex;
   
   public Printer(int tonerlevel,boolean duplex) {
	  if(tonerlevel <=100 && tonerlevel>0) {
		  this.tonerLevel=tonerlevel;
	  }else {
		  this.tonerLevel=0;
	  }
	   this.duplex=duplex;
   }
   
  public Printer(){
	  
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
  
  public int addToner(int amount) {
	  if((amount+this.tonerLevel)<=100) {
		  this.tonerLevel=this.tonerLevel+amount;
		  return this.tonerLevel;
	  }else {
		  return -1;
	  }
  }
  
  
  public int printPages(int pages) {
	  if(this.duplex) {
		  System.out.print("It is a duplex printer");
		  this.pagesPrinted=pages/2;
	  }else {
		  this.pagesPrinted=pages;
	  }
	  
	  return pages;
  }
  
  public static void main (String [] args) {
		int value=new Printer(3,true).printPages(100);
	}
   
}
