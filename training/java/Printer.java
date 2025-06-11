public class Printer{
    private int tonerLevel;
    private int pagesPrinted;
    private boolean duplex;
    public Printer(int tonerLevel,int pagesPrinted,boolean duplex){

    }
    public int addToner(int tonerAmount){
        tonerLevel+=tonerAmount;
        if (tonerLevel>100 || tonerLevel<0){return -1;}
        return tonerLevel;

    }
    public int printPages(int pages){
return -1;
    }
}