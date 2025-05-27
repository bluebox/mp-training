
public class DisplayFinalScore {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int score=10000;
		byte levelCompleted=8;
		short bonus=200;
		boolean gameOver = true;
		if(gameOver) {
			int finalScore=score+(levelCompleted*bonus);
			System.out.print(finalScore);
		}
	}

}
