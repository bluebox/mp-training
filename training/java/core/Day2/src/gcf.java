public class gcf {
    public static void main(String[] args) {
        int num1=12,num2=20;
        System.out.println(Gcf(num1,num2));
    }
    public static int Gcf(int num1,int num2){
        if(num2==0){
            return num1;
        }
        return Gcf(num2,num1%num2);
    }
}
