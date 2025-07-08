public class Problem {
    public static void main(String[] args) {
        int[] arr={1,2,3};
        
        int j=0;
        int s=0;
        int m=Integer.MIN_VALUE;
        while(j<(arr.length-1)){
            System.out.println(arr[j]);
                s+=arr[j];
                j++;
            }
        System.out.println("SUM is"+s);



            for(int i=0;i<arr.length;i++){
                if(m<s){
                    m=s;
                }
                System.out.println(j+"j"+arr[j]);
                s+=arr[j];
                s-=arr[i];
                j++;
                j=j%(arr.length);

            }
            System.out.println("max value is "+m);
        }
        
    }
    

