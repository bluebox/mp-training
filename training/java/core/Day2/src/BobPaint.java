public class BobPaint {
    public static void main(String[] args) {
        System.out.println(getBucketCount(3.4,2.1,1.5,2));
    }
    public static int getBucketCount(double n1,double n2,double n3, int n){
        if(n1<0||n2<0||n3<0||n<0){
            return -1;
        }
        double area=n1*n2;
        int req= (int) (area/n3);
        //System.out.println(req);
        return req+1-n;
    }
}
