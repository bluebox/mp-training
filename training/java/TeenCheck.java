public class TeenCheck {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println(hasTeen(1,2,34));
        System.out.println(hasTeen(13,19,34));
        System.out.println(hasTeen(19,25,13));
        System.out.println(hasTeen(100,100,100));
        System.out.println(hasTeen(13,14,15));
    }
    public static boolean hasTeen(int a,int b,int c) {
        if ( ( a>=13 && a<=19 ) || ( b>=13 && b<=19 ) || ( c>=13 && c<=19 ) ) {
            return true;

        }return false;


    }

}

