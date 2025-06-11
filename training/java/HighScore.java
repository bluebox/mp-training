import java.util.*;
public class HighScore {
    public static void main(String[] args) {
        Scanner S=new Scanner(System.in);
        int a=S.nextInt();
        displayHighScore("swamy",a);

        System.out.println(calculateHighScorePosition(1500));
        System.out.println(calculateHighScorePosition(1000));
        System.out.println(calculateHighScorePosition(500));
        System.out.println(calculateHighScorePosition(100));
        System.out.println(calculateHighScorePosition(25));

    }
    public static void displayHighScore(String name,int position) {
        System.out.println(name+" managed to get into posiotion "+position+" on the high score list");
    }
    public static int calculateHighScorePosition(int Score) {
        if (Score>=1000)
        {return 1;}
        if (Score <1000 && Score >=500) {
            return 2;

        }
        if (Score>=100 && Score <500) {
            return 3;

        }
        return 4;


    }

}

