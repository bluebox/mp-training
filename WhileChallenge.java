
public class WhileChallenge {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Even(5,20);

	}
    
	public static boolean isEvenNumber(int n) {
		if(n%2==0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static void Even(int m, int n) {
		int e=0,o=0;
		for(int i=m;i<=n;i++) {
			if(isEvenNumber(i)) {
				System.out.println(i);
				e++;
				if(e==5) {
					break;
				}
			}
			else {
				o++;
			}
		}
		System.out.println("Odd's found "+o);
		System.out.println("Even's found "+e);
	}
}
