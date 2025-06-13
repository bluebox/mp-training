
public class Printer {
	 public int tonerLevel;
	 public int pagesPrinted;
	 public boolean duplex;
	 public Printer() {
		 this.tonerLevel=0;
		 this.duplex=true;
		 this.pagesPrinted=0;
	 }
	 public int addToner(int tonarAmount) {
		 this.tonerLevel+=tonarAmount;
		 if(this.tonerLevel<0) {
			 this.tonerLevel=0;
			 return -1;
		 }
		 if(this.tonerLevel>100) {
			 this.tonerLevel=100;
			 return -1;
		 }
		 return this.tonerLevel;
	 }
	 public int printPages() {
		 if(this.duplex) {
			 System.out.println("duplex Printer");
		 }
		 int val=(this.duplex)?2*this.pagesPrinted:this.pagesPrinted;
		 return val;
	 }
}
