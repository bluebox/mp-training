package june25;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello World");

        System.out.println("The range of byte values are (" + Byte.MIN_VALUE + " , " + Byte.MAX_VALUE + ")");
        System.out.println("The range of short values are (" + Short.MIN_VALUE + " , " + Short.MAX_VALUE + ")");
        System.out.println("The range of int values are (" + Integer.MIN_VALUE + " , " + Integer.MAX_VALUE + ")");
        System.out.println("The range of long values are (" + Long.MIN_VALUE + " , " + Long.MAX_VALUE + ")");

        System.out.println("The size (in bits) of int values is " + Integer.SIZE);

        int myMaxTest = 2147483647; System.out.println(myMaxTest+1);
        int myMinTest = -2147483648; System.out.println(myMinTest-1);

        long numTest = 2147483648L; // gives error if L is not there

        System.out.println("The range of float values are (" + Float.MIN_VALUE + " , " + Float.MAX_VALUE + ")");
        System.out.println("The range of double values are (" + Double.MIN_VALUE + " , " + Double.MAX_VALUE + ")");

        System.out.println("5/3 = "+5/3+"\n5f/3f = "+5f/3f+"\n5d/3d = "+5d/3d+"\n5.00/3 = "+5.00/3);

        System.out.println("Adding Characters");
        char frstChar = 'A'; char sndChar = 'B';
        System.out.println(frstChar+sndChar);
        System.out.println(""+frstChar+sndChar);

        System.out.println("Compound Assisgnment");
        int a = 10; a-=5.5;
        System.out.println(a);
        double b = 10; b-=5.5;
        System.out.println(b);
 
    }
}
