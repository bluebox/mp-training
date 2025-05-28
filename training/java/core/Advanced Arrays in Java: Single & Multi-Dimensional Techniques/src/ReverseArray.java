import java.util.Arrays;

public class ReverseArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[]= {5,83,49,57,1,0,6};
		int temp;
        String Arr = Arrays.toString(arr);
        System.out.println("Array = "+Arr);
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr.length - (i+1);j++){
                temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
            }
        }
        String stringArr = Arrays.toString(arr);
        System.out.println("Reversed array = "+stringArr);
    }
	}


