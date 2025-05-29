public class CanPack {

    public static void canPack(int a,int b,int c){
        int w = (int) c / 5;
        int rem = c%5;
        if(a < 0 || b< 0 || c<0){
            System.out.println("false");
            return;
        }

        if (w == c){
            System.out.println("true");
            return;
        }
        else if(w > c){
            w = w - c;
        }
        else if(w < c){
            w = 0;
        }

        if((w*5) + rem <= b){
            System.out.println("true");
            return;
        }
        else{
            System.out.println("false");

        }
        
    }
    public static void main(String[] args) {
        canPack(1, 0, 4);
        canPack(1, 0, 5);
        canPack(0, 5, 4);
        canPack(2, 2, 11);
        canPack(-3, 2, 12);
    }
}
