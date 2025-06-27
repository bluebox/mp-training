public class PlayingCat {
    public static void main(String[] args) {
        if(isCatPlaying(true,50)){
            System.out.println("cat is playing");
        }
        else{
            System.out.println("cat is not playing");
        }
    }
    public static boolean isCatPlaying(boolean Summer, int temp){
        if(Summer){
            if(temp>25 && temp<=45){
                return true;
            }
            else return false;
        }
        else{
            if(temp>25 && temp<35){
                return true;
            }
            else return false;
        }
    }
}
