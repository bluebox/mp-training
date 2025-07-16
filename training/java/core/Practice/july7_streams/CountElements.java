package Practice.july7_streams;

import java.util.Arrays;
import java.util.List;

public class CountElements {

	public static void main(String[] args) {
		List<String> color=Arrays.asList("red","blue","green","orange","pink","black","maroon","grey");
		long num=color.stream().filter((mycolor)->mycolor.length()>4).count();
		System.out.println(num);
	}

}
