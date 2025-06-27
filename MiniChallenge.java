package corejavaday_Two;

public class MiniChallenge {

    public static void main(String[] args){
        calculateInterest(100);
    }
    public static void calculateInterest(int amount){
        for(double rate=7.5;rate<=10;rate+=0.25){
            double res=amount*(rate/100);
            System.out.println(res);
        }
    }
}
