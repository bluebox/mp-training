
public class SwitchNatoAlphabet {

	public static void main(String[] args) {
		String str = "CAR";
		for(int i=0;i<str.length();i++) {
		switch(str.charAt(i)) {
		case 'A':
			System.out.print("A Stands for Able\n");
			break;
		case 'B':
			System.out.print("B Stands for Baker\n");
			break;
		case 'C':
			System.out.print("C Stands for Charlie\n");
			break;
		case 'D':
			System.out.print("D Stands for dog\n");
			break;
		case 'E':
			System.out.print("E Stands for Easy\n");
		default:
			System.out.print(str.charAt(i) + " is not found\n");
		}
		}
	}

}
