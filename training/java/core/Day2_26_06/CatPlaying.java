package Day2_26_06;

public class CatPlaying {
	public static void main(String args[]) {
		System.out.println(isCatPlaying(true,40));
		System.out.println(isCatPlaying(false,40));
		System.out.println(isCatPlaying(true,36));
		System.out.println(isCatPlaying(true,20));
		System.out.println(isCatPlaying(false,40));

	}
	public static boolean isCatPlaying(boolean flag,int temp) {
		int low=25,high=35;
		if(flag) {
			high=45;
		}
		return temp>=low && temp<=high;
	}
}
