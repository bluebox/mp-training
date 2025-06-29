public class Printer {
   private int tonerLevel;
   private int pagesPrinted;
   private boolean duplex;
   public Printer(int tonerLevel,boolean duplex){
      this.tonerLevel=tonerLevel;
      this.duplex=duplex;
      this.pagesPrinted=0;
      if(this.tonerLevel<0 && this.tonerLevel>100)this.tonerLevel=-1;
   }

public int addToner(int tonerAmount){
   if(tonerAmount>0 && tonerAmount<=100){
   this.tonerLevel+=tonerAmount;
   if(this.tonerLevel>=0 && this.tonerLevel<=100)return tonerLevel;
   }
   return -1;
}
public int printPages(int pages){
   int pagesToPrint=pages;
   if(duplex){
      pagesToPrint=2*pages;
   }
   this.pagesPrinted+=pagesToPrint;
   return pagesToPrint;
}
public int getPagesPrinted(){
   return pagesPrinted;

}
}
