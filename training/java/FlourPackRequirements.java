public class FlourPackReqirements{
    public static void main(String [] args){
        System.out.println(canPack(1,0,4));
        System.out.println(canPack(1,0,5));
        System.out.println(canPack(0,5,4));
        System.out.println(canPack(2,2,11));
        System.out.println(canPack(3,3,14));
        System.out.println(canPack(-3,2,12));
        System.out.println(canPack(5,0,25));
        System.out.println(canPack(1,8,12));
    }
    public static boolean canPack(int bigCount,int smallCount,int goal){
        if (bigCount<0 || smallCount <0){return false;}
        if(bigCount==0){return (goal<=smallCount);}
        if(smallCount==0){return ((goal<=bigCount*5) && (goal%5==0));}


        return (goal<=(bigCount*5 + smallCount))&&(smallCount>=(goal%5));
    }
}