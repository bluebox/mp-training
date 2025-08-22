import org.omg.CORBA.UShortSeqHelper;

public class DataTypeCh {
    public static void main(String[] args) {
        byte b = 2;
        short s = 4;
        int i = 10;
        long l = 50000 + 10 * (b + s + i);
        System.out.println(l);
    }
}
