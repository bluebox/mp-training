package WebSocket;

public class Main {

	public static void main(String[] args) {
		StringData data = new StringData();

		StringBuilder sb = new StringBuilder();
		while (sb.length() < 10) {
			data.addChar(sb, 'a');
		}
		System.out.println(sb);
	}

}
