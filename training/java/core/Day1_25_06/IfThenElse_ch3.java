package Day1_25_06;

public class IfThenElse_ch3 {
	public static void main(String args[]) {
//		Insert a code segment after the code we've just reviewed:
//		Set the existing score variable to 10,000.
//		Set the existing levelCompleted variable to 8.
//		Set the existing bonus variable to 200.
//		Use the same if condition. Meaning if gameOver is true, then you want to perform the same calculation, and print out the value of the finalScore variable.
	boolean isComplete=true;
	int score=10_000;
	int levelCompleted=8;
	int bonus=800;
	
	int finalScore=score;
	if(isComplete) {
		finalScore+=(bonus*levelCompleted);
	}
	System.out.println(finalScore);
	}
}
