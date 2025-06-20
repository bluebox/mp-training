import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class student implements Serializable{
	private String name;
	private int age;
	public student(String name,int age) {
		this.age=age;
		this.name=name;
	}
	void display() {
		System.out.println(this.name);
		System.out.println(this.age);
	}
}
public class serializable {

	public static void main(String[] args) {
		student a=new student("anand",22);
		try {
			FileOutputStream b=new FileOutputStream("student.ser");
			ObjectOutputStream c=new ObjectOutputStream(b);
			c.writeObject(a);
			c.close();
			b.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		student ans=null;
		try {
			FileInputStream val=new FileInputStream("student.ser");
			ObjectInputStream vals=new ObjectInputStream(val);	
			ans=(student) vals.readObject();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		if(ans!=null) {
			System.out.println("data");
			ans.display();
		}
	}

}
