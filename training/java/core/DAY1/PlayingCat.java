public class PlayingCat {
    public static void main(String[] args) {
        System.out.println(isCatPlaying(true,40));
        
    }
    public static boolean isCatPlaying(boolean summer,int temperature){
        int st=25,end=35;
        if(summer){
            end=45;
        }
        if(temperature>=st&&temperature<=end){
            return true;
        }
        return false;

    }
}
