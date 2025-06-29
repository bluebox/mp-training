package june26_switch;
import java.util.Scanner;
public class NatoRepresentation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the String that needs to convert into NATO");
		String s=sc.next();
		for(char ch:s.toCharArray()) {//converts string s into array of characters while iusing for each loop 
			switch(ch) {
			case 'a'->System.out.print("Able ");
			case 'b'->System.out.print("Baker");
			case 'c'->System.out.print("Charlie");
			case 'd'->System.out.print("Dog");
			case 'e'->System.out.print("Easy");
			default->System.out.print("not found");
			}
			
			
		}
		sc.close();
		}
	}


