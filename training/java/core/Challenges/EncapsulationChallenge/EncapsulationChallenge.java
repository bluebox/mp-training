package Challenges.EncapsulationChallenge;

public class EncapsulationChallenge {
    int tonerLevel;
    int pagesPrinted;
    boolean duplex;

    public EncapsulationChallenge(int tonerLevel, int pagesPrinted, boolean duplex){
        this.tonerLevel = tonerLevel;
        this.pagesPrinted = pagesPrinted;
        this.duplex = duplex;
    }

    public int addToner(int tonerAmount){
        if(tonerAmount+tonerLevel > 100 || tonerAmount+tonerLevel < 0){
            return -1;
        }
        tonerLevel += tonerAmount;
        return tonerLevel;
    }

    public void printPages(int pages){
        pagesPrinted += pages;
        if(duplex){
            System.out.println("This is a duplex printer");
        }
    }

    // public static void main(String args[]){
    //     EncapsulationChallenge ec = new EncapsulationChallenge(20, 20, true);
    //     int tonerQuantity = ec.addToner(20);
    //     System.out.println("Toner quantity after adding is:"+tonerQuantity);
    //     ec.printPages(40);
    // }
}
