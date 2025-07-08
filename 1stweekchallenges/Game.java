import java.util.Scanner;
public class Game {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int score=sc.nextInt();
        int levelcompleted=sc.nextInt();
        int bonus=sc.nextInt();
        
        System.out.println(game(score,levelcompleted,bonus));

    }
    public static int  game(int score,int levelcompleted,int bonus){
            score+=bonus*levelcompleted;
            return score;
        }
}
