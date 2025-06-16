import java.util.*;
class MinFinder{
     private static int readInteger(){
         Scanner sc=new Scanner(System.in);
         int count=sc.nextInt();
         return count;
      }
      private static int[] readElements(int count){
          int[] arr=new int[count];
          Scanner sc=new Scanner(System.in);
          for(int i=0;i<count;i++){
              arr[i]=sc.nextInt();
          }
          return arr;
       }
       private static int findMin(int[] arr){
          int min=arr[0];
          for(int a:arr){
              if(min>a){
                  min=a;
              }
           }
           return min;
        }
        public static void main(String[] args){
            int count=readInteger();
            int[] arr=readElements(count);
            System.out.println("Min value="+findMin(arr));
        }
}

