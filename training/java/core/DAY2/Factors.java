package training.java.core.DAY2;

public class Factors {
    public static void main(String[] args) {
        printFactors(-1);
    }
    public static void printFactors(int number){
        if(number<1){
            System.out.println("Invalid number");            
        }
        for(int i=1;i<=number;i++){
            if(number%i==0){
                System.out.println(i);
            }
        }
    }
}
