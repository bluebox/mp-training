package june26_Oops;
import java.util.Scanner;
public class PaintingJob {
	
	public static int getbucketcount(double w,double h,double a,int extrabuck) {
		if( (w<=0 || h<=0 || a<=0) ||(extrabuck<0))
			return -1;
		double rp=(w*h)-(a*extrabuck);
		double rb=(rp/a);
		if(rb%a==0.0d) {
			return (int)(rb);
		}
		else
			return (int)(rb+1);
	}
	
	public static int getbucketcount(double w,double h,double a) {
		if (w<=0 || h<=0 || a<=0)
				return -1;
		double rp=(w*h)-a;
		int rb=(int)(rp/a);
		return(rb%a==0?rb:rb+1);
		
	}
	
	public static int getbucketcount(double a,double ab) {
		if(a<=0 || ab<=0){
			return -1;
		}
		int rb=(int)(a/ab);
		return (a%ab==0.0d ?rb:rb+1);
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter width,height,area covered per bucket,extra buckets at home");
		double w=sc.nextDouble();
		double h=sc.nextDouble();
		double a=sc.nextDouble();
		int b=sc.nextInt();
		System.out.println("The number of buckets required :"+getbucketcount(w,h,a,b));
		System.out.println("Enter width,height,area covered per bucket");
		double w1=sc.nextDouble();
		double h1=sc.nextDouble();
		double a1=sc.nextDouble();
		System.out.println("The number of buckets required :"+getbucketcount(w1,h1,a1));
		System.out.println("Enter area of the wall,area covered per bucket");
		double a2=sc.nextDouble();
		double aperbucket=sc.nextDouble();
		System.out.println("The number of buckets required :"+getbucketcount(a2,aperbucket));
		sc.close();

	}

}
