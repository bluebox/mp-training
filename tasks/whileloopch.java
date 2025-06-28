import  java.util.Scanner;
public class whileloopch {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("enter the range of numbers");
        int start=sc.nextInt();
        int end=sc.nextInt();
        System.out.println("enter the count:");
        int count=sc.nextInt();
        int c=1;
        while ((start<=end) && (c<=count)){
            if (isEvenNumber(start)){
                System.out.println(start);
                c++;
            }
            start++;
        }
        int ceven=0;
        int codd=0;
        for (int i=0;i<=end;i++){
            if(isEvenNumber(i)){
                ceven++;
            }
            else{
                codd++;
            }

        }
        System.out.println("count of even number is:"+ceven);
        System.out.println("count of odd number is:"+codd);
        
    }
    public static boolean isEvenNumber(int n){
        
        if (n%2==0){
            return true;
        }
        else{
            return false;
        }

    }
    
}
