package Day15_17_07;

public class DebugingMain {
	public static void main(String[] args) {
		int count=0;
		StringBuilder sb=new StringBuilder();
		System.out.println(sb.capacity());
		sb.setLength(17);
		System.out.println(sb.capacity());
		count++;
		System.out.println(count);
	}
}
