import java.util.*;
public class GenericsChallenge2 {

	public static <T extends Number> void swap(T i,T j)
	{
		System.out.println("Befor Swaping i is:"+i+" j is :"+j);
		T temp;
		temp=i;
		i=j;
		j=temp;
		System.out.println("After Swaping i is:"+i+" j is :"+j);
		
	}
	
	public static <T extends Number> void print(List<T> list)
	{
		for(T integer:list)
		{
			System.out.println(integer);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		swap(1,2);
		swap(1.222,1.345); 
		List<Integer> intList = Arrays.asList(1, 2, 3, 4);
        List<Double> doubleList = Arrays.asList(1.1, 2.2, 3.3);

        System.out.println("\nInteger List:");
        print(intList);
        System.out.println("\nDouble List:");
        print(doubleList);

	}

}
