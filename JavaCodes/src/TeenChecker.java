import java.util.*;
public class TeenChecker {
    public static boolean hasTeen(int firstValue,int secondValue,int thirdValue) {
    	if((firstValue>=13 && firstValue<=19)||(secondValue>=13 && secondValue<=19)||(thirdValue>=13 && thirdValue<=19))return true;
    	return false;
    }
    public static boolean isTeen(int age) {
    	if(age>=13 && age<=19)return true;
    	return false;
    }
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int firstValue=sc.nextInt();
		int secondValue=sc.nextInt();
		int thirdValue=sc.nextInt();
		System.out.println(hasTeen(firstValue,secondValue,thirdValue)?"yes he|she hasTeen":"no! he|she is not hasTeen");
		System.out.println("enter a no to check isTeen");
		int fourthValue=sc.nextInt();
		System.out.print(isTeen(fourthValue)?"yes he|she is a teen":"no! he|she is not a teen");
	}

}
