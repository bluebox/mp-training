package June26;

public class OddNumSum {
	
    public static void main(String[] args) {
    	
        System.out.println("The sum of odd numbers in range (1,100) is "+sumOdd(1,100));
        System.out.println("The sum of odd numbers in range (-1,100) is "+sumOdd(-1,100));
        System.out.println("The sum of odd numbers in range (100,100) is "+sumOdd(100,100));
        System.out.println("The sum of odd numbers in range (13,13) is "+sumOdd(13,13));
        System.out.println("The sum of odd numbers in range (100,-100) is "+sumOdd(100,-100));
        System.out.println("The sum of odd numbers in range (100,1000) is "+sumOdd(100,1000));
    }
    
    public static boolean isOdd(int number){
    	
        if (number<0) return false;
        else if(number%2==0) return false;
        return true;
    }
    
    public static int sumOdd(int start, int end){
    	
        if(end<start || start<0 || end<0) return -1;
        int sum=0;
        for(int i=start;i<=end;i++){
            if(isOdd(i)) sum+=i;
        }
        return sum;
    }
}
