package corejavaday_Two;

public class GCD {
    public static void main(String[] args){
        System.out.println(gCD(25,15));
    }
    public static int gCD(int a,int b){
        if(a<10 || b<10) {
            return -1;
        }
        else{
            if(a>b && a%b==0){
                return b;
            }
            else if(a>b && a%b!=0) {
                while (b !=0) {
                    int temp= b;
                    b = a % b;
                    a=temp;
                }
                return a;
            }
            else if(b>a && b%a==0){
                return a;
            }
            else{
                while(a!=0){
                    int temp=a;
                    a=b%a;
                    b=temp;
                }
                return b;
            }
        }
    }
}
