public class TypePromotionRules {

	
	public static void main(String args[]) {
		int marks=93;
		int total=100;
		
		double percentage1=(marks/total)*100.0;
		double percentage2=(double)marks/total *100.0;
		System.out.println(percentage1+" "+percentage2);
	}
}