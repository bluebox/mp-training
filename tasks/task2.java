import java.util.Scanner;
public class task2 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("enter the score:");
        int score=sc.nextInt();
        System.out.println("enter the numberr of levels completed:");
        int levelcompleted=sc.nextInt();
        System.out.println("enter the bnous");
        int bonus=sc.nextInt();
        System.out.println("game over or not:");
        boolean gameover=sc.nextBoolean();
        int finalscore=score;
        if (gameover){
            finalscore+=(levelcompleted*bonus);
            System.out.println("the final score is :"+finalscore);
        }

        

        
    
    }
    
    
}