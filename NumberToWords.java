package corejavaday_Two;
import java.util.Scanner;
public class NumberToWords {
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        int number= input.nextInt();
        numberToWords(number);
    }
    public static int getDigitCount(int number) {
        int count = 0;
        if (number == 0) {
            return 1;
        }
        if (number < 0) {
            return -1;
        }
        while (number > 0) {
            number /= 10;
            count++;;
        }
        return count;
    }

    public static int reverse(int number) {
        int rem,reverseNumber = 0;
        boolean isNegative = false;
        if (number < 0) {
            number = Math.abs (number); //to return absolute value
            isNegative = true;
        }
        while (number > 0) {
            rem = number % 10;
            reverseNumber = reverseNumber * 10 + rem;
            number /= 10;
        }
        if (isNegative == true) {
            return -reverseNumber;
        }
        return reverseNumber;
    }

    public static void numberToWords(int number) {

        if (number < 0) {
            System.out.println("Invalid Value");
        }
        else if (number == 0) {
            System.out.println("Zero");
        }
        else {
            int revnum = reverse(number);
            int numDigCount = getDigitCount(number);
            int revNumDigCount = getDigitCount(revnum);
            String s = "";
            int remain = 0;
            while (revnum > 0) {
                remain= revnum % 10;
                switch(remain) {
                    case 0:
                        s = s + "Zero\n";
                        break;
                    case 1:
                        s = s + "One\n";
                        break;
                    case 2:
                        s = s + "Two\n";
                        break;
                    case 3:
                        s = s + "Three\n";
                        break;
                    case 4:
                        s = s + "Four\n";
                        break;
                    case 5:
                        s = s + "Five\n";
                        break;
                    case 6:
                        s = s + "Six\n";
                        break;
                    case 7:
                        s = s + "Seven\n";
                        break;
                    case 8:
                        s = s + "Eight\n";
                        break;
                    case 9:
                        s = s + "Nine\n";
                        break;
                    default:
                        break;
                }
                revnum /= 10;
            }
            if (numDigCount > revNumDigCount) {
                for(int i = 0; i < (numDigCount - revNumDigCount); i++) {
                    s = s + "Zero ";
                }

            }
            System.out.println(s);
        }

    }
}
