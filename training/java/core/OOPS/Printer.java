package OOPS;


public class Printer {
    int tonerLevel;
    int pagesPrinted;
    boolean duplex;

    Printer(int tonerLevel , int pagesPrinted , boolean duplex){
        this.tonerLevel=tonerLevel;
        this.pagesPrinted = pagesPrinted;
        this.duplex = duplex;
    }

    public void addToner(int n){

        if (n > 100){
            System.out.println("invalid");
        }
        if(this.tonerLevel + n > 100){
            System.out.println("filling more than 100% , reduce it");
        }
        else this.tonerLevel+=n;
    }

    public void printPages(int pages){
        this.pagesPrinted+=pages;
        if(duplex) {
            System.out.println("Duplex printer! printing on both sides");
            System.out.printf("%.0f sheets printed\n",Math.ceil( pages/2));
        }else{
            System.out.println("printing one side..");
            System.out.printf("%d sheets printed\n",pages);
        }
    }

    public static void main(String[] args) {
        Printer obj = new Printer(50,0, true);
        obj.printPages(10);
        obj.addToner(50);
    }
}
