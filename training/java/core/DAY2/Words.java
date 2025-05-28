package training.java.core.DAY2;

public class Words {
    public static void main(String[] args) {
        numberToWords(12321);
    }
    public static void numberToWords(int number){
        if(number<0){
            System.out.println("Invalid input");
            return;
        }
        if(number==0){
            System.out.println("zero "); return;
        }
        int cnt=getDigitCount(number);
        int revnum=reverse(number);
        int i=0;
        while(i<cnt){
            int rem=revnum%10;
                switch (rem) {
                    case 1:System.out.print("one ");                    
                        break;
                    case 2:System.out.print("two ");                    
                    break;
                    case 3:System.out.print("three " );                    
                    break;
                    case 4:System.out.print("four ");                    
                    break;
                    case 5:System.out.print("five ");                    
                    break;
                    case 6:System.out.print("six ");                    
                    break;
                    case 7:System.out.print("seven ");                    
                    break;
                    case 8:System.out.print("eight ");                    
                    break;
                    case 9:System.out.print("nine ");                    
                    break;
            
                default:System.out.print("zero "); 
                    break;
            }
            revnum=revnum/10;
            i++;
        }
        System.out.println();
    }
    // public static int cnt=0; 
     public static int getDigitCount(int number){
        int n=number;
        int cnt=0;
        while(n>0){
            cnt++;
            n=n/10;
        }
        return cnt;
    }
    public static int reverse(int number){
        int n=number;
        int sum=0;
        while (n>0) {
            int rem=n%10;
            sum=sum*10+rem;
            n=n/10;
            // cnt++;
        }
        return sum;
    }
    

}
