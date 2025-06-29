/*
 	in this challenge, we will add validation to the methods 
 	1. for the first method 
 		the seconds parameter should be >= 0
 		
 	2. for the second method
 		the minutes parameter should be >=0
 	
 	if either method is passed an invalid value. print out some type of meaningful message to user
 */
public class SecAndMin {
    public static void main(String[] args) {
        int seconds = 120;
        checkSeconds(seconds);

    }
    public static void checkSeconds(int seconds){
        if ( seconds < 0){
            System.out.print("seconds must be >= 0");
        }else{

            checkSeconds(seconds/60, seconds % 60);
        }
    }
    public static void checkSeconds(int minutes, int seconds)
    {
        if( minutes >= 0){
            int hours = minutes /60;
            minutes %= 60;
            System.out.print(hours +  "h : " +minutes+ "m : " + seconds + "s");
        }
        else{
            System.out.print("Minutes must be >= 0");
        }
    }
}
