public class methods8 {
   
    public static boolean isCatPlaying(boolean summer, int temperature) {
        if (summer) {
            // In summer, temperature should be between 25 and 45
            if (temperature >= 25 && temperature <= 45) {
                return true;
            }
        } else {
            // Not summer, temperature should be between 25 and 35
            if (temperature >= 25 && temperature <= 35) {
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        System.out.println(isCatPlaying(true,10));
    }
}


