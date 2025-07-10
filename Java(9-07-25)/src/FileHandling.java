import java.io.File;
import java.io.IOException;

public class FileHandling {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File f=new File("./testing.txt");
		File f1=new File("./Sreeja/");
		System.out.println(f1.mkdir());
		f.createNewFile();
		if(f.exists())
		{
			System.out.println(f.delete());
		}
		System.out.println(f.createNewFile());
		System.out.println(f.isHidden());
		System.out.println(f.canWrite());
		System.out.println(f.setWritable(false));
		System.out.println(f.canWrite());
		System.out.println(f.setWritable(true));
		System.out.println(f.canWrite());
		File f2=new File("C:\\Users\\saisr\\eclipse-workspace\\Java(9-07-25)");
		for(String fileName:f2.list())
		{
			System.out.println(fileName);
		}
	}

}  
