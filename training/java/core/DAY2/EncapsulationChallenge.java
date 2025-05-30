package training.java.core.DAY2;

public class EncapsulationChallenge {
    public static void main(String[] args) {
            Printer p1= new Printer(100, false);
            System.out.println(p1.printPages(20));
    }
}
class Printer{
    private int tonerLevel,pagesPrinted;
    private boolean duplex;
    public int addToner(int tonerAmount){
        tonerLevel+=tonerAmount;
        if(tonerLevel>100||tonerLevel<0){  
            tonerLevel=100;          
            System.out.println("enter input in from 0-100");
            return -1;
        }
        return tonerLevel;
    }
    public int printPages(int pages){
        pagesPrinted=pages;
        if(duplex){
            System.out.println("its a duplex printer");
            pagesPrinted=(int)Math.ceil(pages/2);
            return pagesPrinted;
        }
        return pagesPrinted;
    }
    public Printer(int tonerLevel, boolean duplex) {
        this.tonerLevel = tonerLevel;
        this.duplex = duplex;
    }
    
}
