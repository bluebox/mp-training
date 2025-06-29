package june25;

public class PrimitiveType {
    public static void main(String[] args) {
        byte byteNum = 10;
        short shortNum = 20;
        int intNum = 50;

        long total = 50000L + 10L*(byteNum+shortNum+intNum);
        System.out.println("Total = "+total);

        int sumNum = byteNum+shortNum+intNum;
        total = 50000L + (10*sumNum);
        System.out.println("Total = "+total);
    }
}
