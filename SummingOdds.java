
public class SummingOdds {

	public static boolean isOdd(int number) {
		if(number<0) {
			return false;
		}
		else if(number%2==0) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public static int SumOdd(int start, int end) {
		int sum=0;
		if(start>0 && end>0 && end>=start) {
		for(int i=start;i<=end;i++) {
			if(!isOdd(i)) {
				continue;
			}
			else {
				sum+=i;
			}
		}
		return sum;
		}
		return -1;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
            System.out.println(SumOdd(1,100));
	}

}
