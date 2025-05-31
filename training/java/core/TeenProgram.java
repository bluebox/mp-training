public class TeenProgram {



    public static void hasTeen(int a,int b,int c){
        if(isTeen(a) || isTeen(b) || isTeen(c)){
            System.out.println("True");
        }
        else{
            System.out.println("False");
        }
    }

    public static boolean isTeen(int a){
        return (a >12  && a<20);
            
        
    }
    public static void main(String[] args) {
        hasTeen(9, 99, 19);
        hasTeen(23, 159, 42);
    }
}
