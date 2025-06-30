import java.util.Scanner;
    public class task8{
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            
            System.out.println("enter a start number: ");
            int startRange = scanner.nextInt();
           
            System.out.println("enter a end number: ");
             int endRange = scanner.nextInt();
            int sum = 0;
            int count = 0;
            for(int i = startRange;i<=endRange; i++){
                if (i % 5 == 0 && i % 3 == 0){
                    System.out.println("found a number:" + i);
                    sum+=i;
                    count ++;
                    if(count==5){
                        break;
                    }


                
                }
            }
            System.out.println(sum);
        }


    }
