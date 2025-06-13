import java.util.ArrayList;
import java.util.List;

public class WaterCalculator {
	public static void main(String[] args) {
		ArrayList<Integer> l=new ArrayList<Integer>(List.of(2,3,1,14,6)) ;
		int i=0;
		int j=l.size()-1;
		int m=Math.min(l.get(i), l.get(j))*j;
		while(true) {
			if(l.get(i)>l.get(j)) {
				j--;
			}
			else if(l.get(i)<l.get(j)) {
				i++;
			}
			else {
				if(l.get(i+1)>l.get(j-1)) {
					j-=2;
				}
				else {
					i+=2;
				}
			}
			if(i>=j) {
				break;
			}
			
			m=Math.max((Math.min(l.get(i), l.get(j))*(j-i)), m);
		}
		System.out.println(m);
	}
}
