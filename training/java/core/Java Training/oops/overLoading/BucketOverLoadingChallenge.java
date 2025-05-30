package oops.overLoading;

public class BucketOverLoadingChallenge {
	
		public static int getBucketCounter (double width,double height,double area,int nBuckets){
			double areaOfWall=width * height;
			int nBucketsAre= getBucketCounter(areaOfWall,area);
			return nBucketsAre>nBuckets?(nBucketsAre-nBuckets):0;
		}
		
		public static int getBucketCounter (double wallArea,double area){
			int nBucketsAre= (int) Math.ceil(wallArea/area);
			return nBucketsAre;
		}
		
		public static int getBucketCounter (double width,double height,double area){
			double areaOfWall=width * height;
			int nBucketsAre= getBucketCounter(areaOfWall,area);
			return nBucketsAre;
		}
		
		public static void main(String[] args) {
			double width=5.75,height=6.5;
			double area=8.5,wallArea=87.36;
			int nBuckets=1;
			System.out.println("With four args : "+getBucketCounter(width,height,area,nBuckets));
			System.out.println("With Three args : "+getBucketCounter(width,height,area));
			System.out.println("With Two args : "+getBucketCounter(wallArea,area));
		}
}
