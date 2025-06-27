public class MinToYearsAndDays {
    public static void main(String[] args) {
         int minutes = 525600;
         printYearsandDays(minutes);
    }
    public static void printYearsandDays(long minutes){
        if(minutes<0){
            System.out.println("Invalid Value");
        }
        else{
            int y,d;   // 1 year = 525600 minutes   ,  1 day = 1440 minutes
            y =(int) (minutes)/525600;
            int rem = (int) (minutes % 525600);
            d = (int) rem/1440;

            System.out.println(minutes + " min = " + y +" y and "+ d +" d");
        }
    }
}
