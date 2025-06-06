package FileManagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CreatingACSVFile {
    public static final String path="/home/developer/eclipse-workspace/JavaCodes/src/FileManagement/resume.csv";
	public static void main(String[] args) throws IOException {
		 File f=new File(path);
		 if(!f.exists()) {
			 f.createNewFile();
		 }
		 FileWriter wr=new FileWriter("/home/developer/eclipse-workspace/JavaCodes/src/FileManagement/resume.csv");
		 wr.append("name,age,city\n");
		 wr.append("anand,22,hyd\n");
		 wr.flush();
		 BufferedReader br=new BufferedReader(new FileReader(path));
		 String ch="";
		 while((ch=br.readLine()) != null) {
			 String []st=ch.split(",");
			 for(String ele:st)System.out.println(ele);
		 }
	}

}
