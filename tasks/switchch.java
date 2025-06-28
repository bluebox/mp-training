import java.util.Scanner;
public class switchch {
    public static void main(String[] args) {
        String A="Able";
       String B="baker";
       String C="Charlie";
       String D="Dog";
       String E="elephant";
       String F="fox";
        Scanner sc=new Scanner(System.in);
        System.out.println("enter the String:");
        String input=sc.nextLine();
        
        for(char c:input.toCharArray()){
            switch(c){
                case 'A': case 'a':
                System.out.print(A+" ");
                break;
                case 'B': case 'b':
                System.out.print(B+" ");
                break;
                case 'C': case 'c':
                System.out.print(C +" ");
                break;
                case 'D': case 'd':
                System.out.print(D+" ");
                break;
                case 'E': case 'e':
                System.out.print(E+" ");
                break;
                case 'F': case 'f':
                System.out.print(F+" ");
                break;
                default:
                System.out.print("unkown charcater"+c);
                break;
            }

        }
       

    }
    
}
