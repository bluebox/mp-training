package FileManagement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
class Student implements Serializable {
	public String name;
	public int age;
	public Student(String name,int age) {
		this.name=name;
		this.age=age;
	}
}
public class ObjectsIntoFileUsingSerializable {

	public static void main(String[] args) throws IOException {
		File f=new File("resume.txt");
		if(!f.exists()) {
			f.createNewFile();
			System.out.println("file is created");
		}
		Student st=new Student("sfsjlfslj",22);
		try(FileOutputStream f1=new FileOutputStream("resume.txt",true)){
			ObjectOutputStream a=new ObjectOutputStream(f1);
			a.writeObject(st);
			System.out.println("added");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		try(FileInputStream a=new FileInputStream("resume.txt")){
			ObjectInputStream b=new ObjectInputStream(a);
			while(true) {
				try {
					Student stt=(Student)b.readObject();
					System.out.println(stt.name);
				}
				catch(Exception e) {
					break;
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
