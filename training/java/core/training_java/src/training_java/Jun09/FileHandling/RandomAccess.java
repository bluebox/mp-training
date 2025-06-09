package FileHandling;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

public class RandomAccess {
public static List<Employee> createData() {
	var empList=List.of(new Employee(123,45321.65,"Alex","Jain"),new Employee(234,32156.87,"Bob","Don"));
	return empList;
}
public static void main(String[] args) throws IOException {
	RandomAccessFile ra=new RandomAccessFile("samp.dat","rw");
	var empList=createData();
	ra.seek(0);
	ra.writeInt(empList.get(0).empId());
	ra.writeDouble(empList.get(0).salary());
	ra.writeUTF(empList.get(0).firstName());
	ra.writeUTF(empList.get(0).lastName());
	ra.close();
}
}
