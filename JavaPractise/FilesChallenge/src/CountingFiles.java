import java.io.File;

public class CountingFiles {
	public static void main(String[] args) {
		File f=new File("C:\\Users\\ADMIN\\eclipse-workspace");
		System.out.println(f.exists());
		String[] s=f.list();
		long countDirectories=0;
		long countFiles=0;
		for(String str:s) {
			System.out.println(str);
			File f1=new File(f,str);
			if(f.isDirectory()) {
				countDirectories++;
			}
			if(f1.isFile()) {
				countFiles++;
			}
		}
		System.out.println("The total number of Directories are :"+countDirectories);
		System.out.println("The total number of Files are :"+countFiles);
	}

}
