public class TimeCalculator {
    public static void main(String[] args) {
        printYearsAndDays(1051200);
    }
    public static void printYearsAndDays(long minutes){
        if(minutes <0){
            System.out.println("Invalid Input");return;
        }
        long days=0,years=0;
        // 1year=   60*24*365
        years=minutes/525600;
        days=(minutes%525600)/(60*24);    
        System.out.println(minutes+" min = "+years +" y and "+days+" d");   
    }
}
