package variables;

public class Variables {
    public static void main(String[] args) {
        int myNumber = 10;
        // int outOfRangeNumber = 1036378356676788;
        long longNumber = 1036378356676788L;

        double myDouble = 5.99;
        char myLetter = 'D';
        boolean myBool = true;
        String myText = "Hello, Java!";

        //the below print statement is not executed as the number is out of range for type (int)
        // System.out.println("Out of range number is"+outOfRangeNumber);
        System.out.println("Integer number is:"+myNumber);
        System.out.println("Long number is:"+longNumber);
        System.out.println("double number is:"+myDouble);
        System.out.println("Character is:"+myLetter);
        System.out.println("Boolean is:"+myBool);
        System.out.println("String is:"+myText);
    }
}
