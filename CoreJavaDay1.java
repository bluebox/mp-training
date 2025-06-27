import java.lang.*;
import java.util.Scanner;
public class CoreJavaDay1
{
    public static void main(String[] args)
    {
        //Ranges of various datatypes
        /* here Integer,Short,Byte....all are wrapper classes that
        gives additional properties to primitive types */
        System.out.println("Integer ranges from "+Integer.MIN_VALUE+" to "+Integer.MAX_VALUE);
        System.out.println("Integer ranges from "+Byte.MIN_VALUE+" to "+Byte.MAX_VALUE);
        System.out.println("Integer ranges from "+Short.MIN_VALUE+" to "+Short.MAX_VALUE);
        System.out.println("Integer ranges from "+Long.MIN_VALUE+" to "+Long.MAX_VALUE);
        System.out.println("Integer ranges from "+Float.MIN_VALUE+" to "+Float.MAX_VALUE);
        System.out.println("Integer ranges from "+Double.MIN_VALUE+" to "+Double.MAX_VALUE);

        //Type Casting;
        int value=127;
        byte result=(byte)(value); //explicit Type Casting
        long value1=value;//Implicit
        System.out.println(result+" "+value1);

        //convert pounds to kilos
        Scanner input = new Scanner(System.in);
        double pounds = input.nextDouble();

        double kilos=pounds*0.45359237;
        System.out.println("Number of Kilograms for "+pounds+" pounds = "+kilos);

        //how many ways we can assign characters
        char alpha='a';  //normal character
        char uni_alpha='\u0041';  //unicode value
        char ascii_alpha=65;  //ascii values
        char ascii_beta=66;

        System.out.println(alpha+" "+uni_alpha+" "+ascii_alpha);

        // when we add two characters it will perform addition
        System.out.println(ascii_beta+ascii_alpha);

        //to overcome it we use "" at the start
        System.out.println(""+ascii_beta+ascii_alpha);

        // various arithmatic operations
        int int_value=100;
        int int_value1=2;
        System.out.println(int_value1+int_value);
        System.out.println(int_value1-int_value);
        System.out.println(int_value1*int_value);
        System.out.println(int_value1/int_value);
        System.out.println(int_value1%int_value);

        //compound assignment operation
        //it will perform operation alongside type casting

        int b=100;
        b-=4.5;
        System.out.println(b);

        //challenge2
        double cha_val=20.00;
        double cha_val2=80.00;
        double resul=(cha_val2+cha_val)*100.00;
        double rem=resul%40.00;
        boolean isrem=(rem==0)?true:false;
        System.out.println(isrem);
        if(isrem!=true)
            System.out.println("got some remainder");

        //primitive types-challenge3
        byte b_val=126;
        short s_val=129;
        int i_val=10000;
        long result_val=50000+10*(b_val+s_val+i_val);
        System.out.println(result_val);

        //methods that are used for reusability purpose so that the code can be easily understandable
        int n=100;
        System.out.println(isPalin(n));

        //if then else challenge
        int score=10000;
        int levelcompleted=8;
        int bonus=200;
        boolean gameover=true;

        int finalScore=score;

        if(gameover){
            finalScore+=levelcompleted+score;
            System.out.println("the final Score was: "+finalScore);
        }

    }
    //method with parameters
    public static boolean isPalin(int n) {
        int temp = n;
        int rev = 0;
        while (temp != 0) {
            int d = temp % 10;
            rev = rev * 10 + d;
            temp /= 10;
        }
        boolean flag = (n == rev);
        return flag;
    }

}
