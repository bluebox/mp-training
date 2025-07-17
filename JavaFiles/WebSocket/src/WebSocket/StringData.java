package WebSocket;

public class StringData {

	private StringBuilder sbuild = new StringBuilder();
	private int chars = 0;

	public void addChar(StringBuilder sb, char c) {
		this.sbuild.append(c);
		chars++;

	}

}
