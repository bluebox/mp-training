package day6.equalUpToThreeDecimal;

public class EqualDouble {
public static boolean equalUpToThreeDecimal(double d1,double d2) {
	double temp=0.001;
	return Math.abs(d1-d2)<temp;
}
}
