package june30_collections;

public class MinElement {
	
	public static int[] readIntegers(String input) {
		String[] data=input.trim().split(",");
		
		int[] arr=new int[data.length];
		
		for(int i=0;i<data.length;i++) {
			arr[i]=Integer.parseInt(data[i]);
		}
		return arr;
	}
	
	public static int Min_Element(int[] arr) {
		int mini=Integer.MAX_VALUE;
		for(int num:arr) {
			if(num<mini) {
				mini=num;
			}
		}
		return mini;
	}

}
