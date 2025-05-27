public class TeenNumber {
    public static void main(String[] args) {
       System.out.println( hasTeen(2,13,34));
    }
    public static boolean hasTeen(int a,int b,int c){
        if(isTeen(a)|| isTeen(b)|| isTeen(c)){
            return true;
        }
        return false;
    }
    public static boolean isTeen(int a){
        if(a>=13&&a<=19 ){
            return true;
        }
        return false;
    }
}
