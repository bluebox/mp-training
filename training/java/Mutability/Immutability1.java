package Mutability;

public class Mutability1 {
    public static void main(String[] args) {
        String s1="Rakesh";
        String s2="Rakesh";
        System.out.println(s1==s2); 
        s1+="ram";
        System.out.println("s1==s2");
        String s3=new String("Rakeshram");
        System.out.println(s1==s3);
    }
}
