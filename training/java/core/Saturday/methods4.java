public class methods4 {
     public static boolean hasEqualSum(int num1,int num2,int num3){
          int sum = num1+num2;
          if(sum == num3){
              return true;
          }
          return false;
    }
    public static void main(String[] args) {
        System.out.println(hasEqualSum(2, 4,6 ));
    }
}
