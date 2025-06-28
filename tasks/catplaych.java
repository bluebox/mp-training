import java.util.Scanner;
public class catplaych {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("is it summer?(true/false):");
        boolean s=sc.nextBoolean();
        System.out.println("enter the temperature:");
        int t=sc.nextInt();
        System.out.println(iscatplaying(s, t));
        
    }
    public static boolean iscatplaying(boolean summer ,int temp){
        if (summer && (temp>=25 && temp<=45)){
            return true;
        }
        else if(!summer && (temp>=25 && temp<=35)){
            return true;
        }
        else{
            return false;
        }



    }
    
}
