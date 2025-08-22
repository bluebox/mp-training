
public class Score {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
          boolean gameOver = true;
          int score=1000;
          int levelCompleted=8;
          int bonus=200;
          int finalScore=score;
          
          if(gameOver) {
        	  finalScore += (levelCompleted  +  bonus);
        	  System.out.println("Your Final Score was "+ finalScore);
          }
	}

}
