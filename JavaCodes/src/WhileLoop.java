
public class WhileLoop {
    public static boolean isEven(int counter) {
    	return counter%2==0?true:false;
    }
	public static void main(String[] args) {
		 int counter=5;
		 while(counter++<=20) {
			 if(isEven(counter))System.out.println(counter);
		 }
	}

}
