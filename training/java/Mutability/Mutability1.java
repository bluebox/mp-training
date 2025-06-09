package Mutability;

public class Mutability1 {
    public static void main(String[] args) {
        StringBuffer sb1= new StringBuffer("Rakesh");
        StringBuffer sb2= new StringBuffer("Rakesh");
        System.out.println(sb1==sb2); // false, different references
        sb1.append("ram");
        System.out.println(sb1==sb2); // false, still different references
        StringBuffer sb3= new StringBuffer("Rakeshram");
        System.out.println(sb1==sb3); // false, different references
    
        StringBuilder sb4 = new StringBuilder("Rakesh");
        StringBuilder sb5 = new StringBuilder("Rakesh");
        System.out.println(sb4==sb5); // false, different references
        sb4.append("ram");
        System.out.println(sb4==sb5); // false, still different references
        StringBuilder sb6 = new StringBuilder("Rakeshram");
        System.out.println(sb4==sb6); // false, different references
    }
}
