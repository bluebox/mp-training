public class methods {
    public static void main(String[] args) {
        int a=5,b=10;
        System.out.println("a = "+a+" b = "+b);
        int[] ans =swap(a,b);
        a=ans[0];
        b=ans[1];
        System.out.println("a = "+a+" b = "+b);

    }
    public static int[]  swap(int a , int b){
        a=a+b;
        b=a-b;
        a=a-b;
        return new int[]{a,b};
    }
}
