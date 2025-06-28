package June26;

public class EvenOdd {
	public static void main(String[] args) {
		int i=5;
		int counter=1;
		int ecount=0; // even number count
		int ocount=0; //odd number count
        System.out.println("5 Even Numbers from 5 to 20 are");
        while(i<=20 && counter<=5){
            if(isEvenNumber(i)) {
            	ecount++;
            	counter++;
            	System.out.println(i);
            }
            else {
            	ocount++;
            }
            i++;
        }
        System.out.println("The total number of odd numbers found are "+ocount);
        System.out.println("The total number of even numbers found are "+ecount);
    }
	
    public static boolean isEvenNumber(int number){
        if(number%2==0) return true;
        return false;
	}
}
