public class PlayingCat {
    public static void main(String[] args) {
        System.out.println("is Cat Playing (true,10) : "+isCatPlaying(true,10));
        System.out.println("is Cat Playing (false,36) : "+isCatPlaying(false, 36));
        System.out.println("is Cat Playing (false, 35) : "+isCatPlaying(false, 35));
    }
    public static boolean isCatPlaying(boolean isSummer, int temperature){
        if ( isSummer ){
            return (temperature <= 45 && temperature >= 25);
        }
        return (temperature <= 35 && temperature >= 25);
    }
}
