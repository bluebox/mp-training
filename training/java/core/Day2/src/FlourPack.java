public class FlourPack {
    public static void main(String[] args) {
        System.out.println(canPack(1,0,5));
    }
    public static boolean canPack(int bigC,int smallC,int goal){
        int big=Math.min(goal/5,bigC);
        int remaining=goal-(big*5);
        return remaining<=smallC;
    }
}

