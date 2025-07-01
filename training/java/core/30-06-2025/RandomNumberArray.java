import java.util.*;
public class RandomNumberArray {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		Random random=new Random();
		Scanner s=new Scanner(System.in);
		System.out.println("Enter the size of the array :");
		int n=s.nextInt();
	
		int numbers[]=new int[n];
		for(int i=0;i<n;i++)
		{
			numbers[i]=random.nextInt();
		}
		System.out.println("Numbers Before Sorting :"+Arrays.toString(numbers));
		
		Arrays.sort(numbers);
		int i=0,j=n-1;
		while(i<n)
		{
			int temp=numbers[i];
			numbers[i]=numbers[j];
			numbers[j]=temp;
			i++;
			j--;
		}
		
		System.out.println("Numbers After Sorting :"+Arrays.toString(numbers));
		

	}

}
