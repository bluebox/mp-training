package june25;

public class PoundsToKilo {
    public static void main(String[] args) {
        // 1 pound = 0.45359237
        double amount = 200d;
        //double amount = 3_000_000.4_567_890d;
        System.out.print("Amount in pounds is "+amount+" \nAmount in kilograms is "+amount*0.45359237);
    }
}
