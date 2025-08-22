import java.nio.file.Path;

public class FileBasics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Path path=Path.of("./testing.txt");
		printPathInfo(path);
	}
	private static void printPathInfo(Path path)
	{
		System.out.println("Path: "+path);
		System.out.println("filename= "+path.getFileName());
		System.out.println("parent ="+path.getParent());
		Path absolutePath=path.toAbsolutePath();
		System.out.println("Absolute path:= "+absolutePath);
		System.out.println("Absolute Path Root:= "+absolutePath.getRoot());
		System.out.println("Root= "+path.getRoot());
		System.out.println("isAbsolute= "+path.isAbsolute());
	}

}
