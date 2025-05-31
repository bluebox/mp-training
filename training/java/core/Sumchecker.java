public class Sumchecker {
    public static boolean hasEqualSum(int a,int b , int c){
        return a + b == c;
    }
    public static void main(String[] args) {
        int a=1;int b=1;int c=2;
        System.out.println(hasEqualSum(a, b, c));
    }
}
