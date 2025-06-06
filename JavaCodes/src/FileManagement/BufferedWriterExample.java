package FileManagement;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedWriterExample {
	public static final String path="/home/developer/eclipse-workspace/JavaCodes/src/FileManagement/example.txt";
	public static void main(String[] args) throws IOException {
		BufferedWriter wr=new BufferedWriter(new FileWriter(path,true));
		wr.append("jai shree ram\n");
		wr.append("sldfjsdjlfsd fsdlfjsdflsdlfdljf\n");
		wr.close();
	}

}
