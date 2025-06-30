import java.util.Scanner;
public class task6 {
    public static boolean isEvenNumber(int number){
        return number %  2== 0;
    }
    public static void main (String[] args) {
       
        int c =5;
        int startRange = 5;
        int endRange = 20;
        int currentNumber = startRange;
        System.out.println("even number between " + startRange + "and " + endRange +"(inclusive:)");
        int evenNumbersFound = 0;
        int oddNumbersFound = 0;
        while(currentNumber <= endRange && c!=0) {
            if(isEvenNumber(currentNumber)){
                System.out.println(currentNumber);
                evenNumbersFound++;
                c--;
                
            }
            else {
        oddNumbersFound++;
    }
    currentNumber++;
        
        


        
        
       

System.out.println("no.of evennumbers found: " + evenNumbersFound);
System.out.println("no.of odd numbers found: "+ oddNumbersFound);
}}

    
