package day7.hasSharedDigit;
import java.util.*;
public class SharedDigit {
public static boolean checkShared(int t1,int t2) {
	if((t1<=9 || t1>99)||(t2<=9 || t2>99))
		return false;
	HashSet<Integer>hs=new HashSet<Integer>();
	int temp1=t1;
	while(temp1>0) {
		hs.add(temp1%10);
		temp1=temp1/10;
	}
	int temp2=t2;
	while(temp2>0) {
		if(hs.contains(temp2%10))
			return true;
		temp2=temp2/10;
	}
	return false;
}
}
