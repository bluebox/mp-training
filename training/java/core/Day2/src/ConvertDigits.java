public class ConvertDigits {
    public static void main(String[] args) {
        System.out.println(NumToWords(321));
    }
    public static String NumToWords(int num){
        StringBuilder ans=new StringBuilder();
        num=rev(num);
        while(num>0){
            int rem=num%10;
            switch (rem){
                case 1:
                    ans.append("One");
                    break;
                case 2:
                    ans.append("Two");
                    break;
                case 3:
                    ans.append("Three");
                    break;
                case 4:
                    ans.append("Four");
                    break;
                case 5:
                    ans.append("Five");
                    break;
                case 6:
                    ans.append("Six");
                    break;
                case 7:
                    ans.append("Seven");
                    break;
                case 8:
                    ans.append("Eight");
                    break;
                case 9:
                    ans.append("Nine");
                    break;
                case 0:
                    ans.append("Zero");
                    break;
                default:
                    ans.append("");
            }
            num/=10;
        }
        return ans.toString();
    }
    public static int rev(int num){
        int sum=0;
        while(num>0){
            sum=sum*10;
            sum+=num%10;
            num/=10;
        }
        return sum;
    }
}
