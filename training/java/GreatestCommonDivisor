public class GreatestCommonDivisor {
    public static void main(String[] args) {
        System.out.print(getGreatestCommonDivisor(25,15));
        System.out.println(getGreatestCommonDivisor(12, 30));
        
        System.out.println(getGreatestCommonDivisor(9, 18));
        System.out.println(getGreatestCommonDivisor(81, 163));

    }
    public static int getGreatestCommonDivisor(int first,int second){
        if(first<10 || second<10){
            return -1;
        }
        if (first<second){
            int temp=first;
            first=second;
            second=temp;

        }
        while(first%second!=0){
            int x=second;
            second=first%second;
            first=x;

        }
        return second;
    }
}
