package Day5.MethodChallenge;

public class MethodChallenge {
public static void main(String args[]) {
	String name1="ram",name2="lakshman",name3="bharath",name4="shatrugna",name5="vali";
	int score1=1500,score2=1000,score3=500,score4=100,score5=25;
	
	displayHighScorePosition(name1,calculateHighScorePosition(score1));
	displayHighScorePosition(name2,calculateHighScorePosition(score2));
	displayHighScorePosition(name3,calculateHighScorePosition(score3));
	displayHighScorePosition(name4,calculateHighScorePosition(score4));
	displayHighScorePosition(name5,calculateHighScorePosition(score5));
	
}
public static int calculateHighScorePosition(int score) {
	int res;
	if(score>=1000)
		res=1;
	else if(score>=500 && score<1000)
		res=2;
	
	else if(score>=100 && score <500)
		res=3;
	else 
		res=4;
	
	return res;
}


public static void displayHighScorePosition(String name,int position) {
	System.out.println(name+" managed to get in to position "+position);
}
}
