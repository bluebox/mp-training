public class SharedDigit {
    public static void main(String[] args) {
        int num1=11,num2=100;
        System.out.println(hasSharedDigit(num1,num2));
    }
    public static boolean hasSharedDigit(int num1,int num2){
        boolean ans=false;
        if(num1<10 || num2>99){
            return ans;
        }
        else{
            while(num1>0){
                int a=num1%10;
                int temp=num2;
                while(temp>0){
                    if (a==temp%10){
                        return true;
                    }
                    else{
                        temp=temp/10;
                    }
                }
                num1/=10;
            }
        }
        return ans;
    }
}
