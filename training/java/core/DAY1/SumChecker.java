public class SumChecker {
    public static void main(String[] args) {
        System.out.println(hasEqualSum(0,0, 0));
    }
    public static boolean hasEqualSum(int a,int b,int c){
        if((a+b)==c){
            return true;
        }
        return false;
    }
}
