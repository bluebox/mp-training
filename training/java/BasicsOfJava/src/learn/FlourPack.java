package learn;
public class FlourPack {
    public static void main(String[] args) {
       System.out.println(canPack(2,5,7));
    }
    public static boolean canPack(int bigCount,int smallCount, int goal){
        int rem = goal;
        if((bigCount < 0) || (smallCount < 0)){
            return false;
        }
        int required = goal / 5;
        if(bigCount >= required){
            rem=rem%5;
            if(smallCount >= rem){
                return true;
            }
            else
            {
                return false;
            }
        }
        return false;
    }
}