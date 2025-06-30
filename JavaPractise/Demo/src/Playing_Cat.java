
public class Playing_Cat {

	public static void main(String[] args) {
		System.out.println(isCatPlaying(true,10));
		System.out.println(isCatPlaying(false,36));
		System.out.println(isCatPlaying(false,35));
		

	}
	public static boolean isCatPlaying(boolean bool,int temp) {
		boolean plays=false;
		if(bool==true) {
			if(temp>=25 && temp<=45) {
				plays=true;
			}
			else {
				plays=false;
			}
		}
		else {
			if(temp>=25 && temp<=35) {
				plays=true;
			}
			else {
				plays=false;
			}
		}
		
		return plays;
	}

}
