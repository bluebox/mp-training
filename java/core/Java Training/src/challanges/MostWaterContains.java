package challanges;

public class MostWaterContains {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int[] water = new int[20];
		int[] water = {2,5,3,1,6};
//		int[] water = {1,8,6,2,5,4,8,3,7};
		int n=water.length;
		int left=0;
		int right = n-1;
		int maxArea = 0;
		while(left < right) {
			int height = Math.min(water[left], water[right]);
			int width = right - left;
			int area = height * width;
			maxArea = Math.max(maxArea, area);
			if(water[left] < water[right])
				left++;
			else
				right--;
		}
		System.out.println("Max water storage area is : "+ maxArea);
	}

}
