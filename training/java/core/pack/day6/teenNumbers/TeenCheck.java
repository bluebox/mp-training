package day6.teenNumbers;

public class TeenCheck {
public static boolean hasTeen(int t1,int t2,int t3) {
	return (isTeen(t1)||isTeen(t2)||isTeen(t3));
}
public static boolean isTeen(int t1) {
	return t1>=13 && t1<=19;
}
}
