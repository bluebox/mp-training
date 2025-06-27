public class Factors {
    public static void main(String[] args) {
        int num=24;
        printFactors(num);
    }
    public static void printFactors(int num){
        for(int i=1;i<=num;i++){
            if(num%i==0){
                System.out.println(i);
            }
        }
    }
}
