public class EveryNthChar {
    public char[] everyNthChar(char[] arr,int n){
        if(arr==null || n<=0 || n>arr.length) {
            return arr;
        }
        int len=arr.length/n;
        char[] res=new char[len];
        int j=0;
        for(int i=n-1;i<arr.length;i+=n){
          res[j]=arr[i];
          j++;
        }
        return res;
    }
}
