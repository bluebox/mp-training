package day7.sumFirstAndLast;

public class SumEdge {
public static int addEdgeDigits(int num) {
	int sum,t2=0;
	
	int temp=num;
	int t1=temp%10;
	while(temp>0) {
		t2=temp%10;
		temp=temp/10;
	}
	sum=t1+t2;
	return sum;
}
}
