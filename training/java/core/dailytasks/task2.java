import java.util.Scanner;
public class task2 {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        System.out.println("enter the score:");
        int score=in.nextInt();
        System.out.println("enter the numberr of levels completed:");
        int levelcompleted=in.nextInt();
        System.out.println("enter the bnous");
        int bonus=in.nextInt();
        System.out.println("game over or not:");
        boolean gameover=in.nextBoolean();
        int finalscore=score;
        if (gameover){
            finalscore+=(levelcompleted*bonus);
            System.out.println("the final score is :"+finalscore);
        }

        

    }     
    
}