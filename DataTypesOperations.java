import java.lang.reflect.Type;

public class DataTypesOperations {
    public static void main(String[] args) {
        //Ranges of different data types using Wrapper Classes
        System.out.println("Byte Range : (" + Byte.MIN_VALUE + "," + Byte.MAX_VALUE + ")" );
        System.out.println("Short Range : (" + Short.MIN_VALUE + "," + Short.MAX_VALUE + ")" );
        System.out.println("Integer Range : (" + Integer.MIN_VALUE + "," + Integer.MAX_VALUE + ")" );
        System.out.println("Long Range : (" + Long.MIN_VALUE + "," + Long.MAX_VALUE + ")" );
        System.out.println("Double Range : (" + Double.MIN_VALUE + "," + Double.MAX_VALUE + ")" );
        System.out.println("Float Range : (" + Float.MIN_VALUE + "," + Float.MAX_VALUE + ")" );

        System.out.println(Long.SIZE);

        //Different Operations On Different Data Types
        int val = 10;
        long num = val * val;
        short snum = (short) (num);  //Type Casting
        System.out.println("Integer Value is :" + val);
        System.out.println("Obtained long Value is :"+ num);
        System.out.println("Converted short Value is :"+ snum);

        char myChar = 'C';
        System.out.println(myChar + 'D');

        boolean result = true;
        char capitalC = 'C';
        byte b = 100;
        short s = 10000;
        int i = 100000;
        float pi =  3.14_15F;
        double d = 20.877d;

        System.out.println("Total Integer Vslue is :"+ (int)(b*i)+(d-pi));

        char c = '\u0050';
        char syn = '\u003F';

        System.out.println(c);
        System.out.println(syn);


    }
}
