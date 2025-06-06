package FileManagement;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterExample {
	public static final String path="/home/developer/eclipse-workspace/JavaCodes/src/FileManagement/example.txt";
	public static void main(String[] args) throws IOException {
		FileWriter fr=new FileWriter(path,true);
		fr.append("fsdfljsd sfjslfjsdlfsdj\n");
		fr.close();
	}

}
