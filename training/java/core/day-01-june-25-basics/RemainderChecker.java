package day_1_june25_basics;

public class RemainderChecker {
    public static void main(String[] args) {
        double first=20.00;
        double second=80.00;
        double sum=(first+second)*100.00;
        double remainder=sum%40;
        boolean isRemainderZero= (remainder==0);
        System.out.println("Boolean is "+isRemainderZero);
        if(!isRemainderZero) {
            System.out.println("Got some remainder");
        }
        else {
            System.out.println("Got no remainder");
        }
    }
}
