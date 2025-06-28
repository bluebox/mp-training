public class DataTypesChallenge {

    public static void main(String[] args) {

        byte myByte = 10;
        short myShort = 200;
        int myInt = 1000;

        long myLong = 50000L + 10L * (myByte + myShort + myInt);

        System.out.println("Result: " + myLong);
    }
}
/* Challenge
Create:

A byte variable with any valid value

A short variable with any valid value

An int variable with any valid value

A long variable set to 50000 and add
10 times the sum of the values of the those 3 variable

*/