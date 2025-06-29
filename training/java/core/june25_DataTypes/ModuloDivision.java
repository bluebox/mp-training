package june25_DataTypes;


public class ModuloDivision {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double a=20.00;
		double b=80d;
		double c=(a+b)*100d;
		double d=c%40D;
		boolean result=(d==0d)?true:false;
		System.out.println("Result : "+result);
		System.out.println(result==false?"got some Remainder":"");


	}

}
