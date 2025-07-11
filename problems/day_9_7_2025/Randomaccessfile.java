package day_9_7_2025;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Randomaccessfile {
	private  static  final Map<Long,Long> IndexedIds=new LinkedHashMap<>();// stores the record index and the fiel position
	public static int recordsnum=0;
	public static void main(String[] args) throws IOException {
           Scanner sc=new Scanner(System.in);
           System.out.println("Eneter the Employee record index for corresponding employee");
           Long id=sc.nextLong();
		try (RandomAccessFile employeedata = new RandomAccessFile("EmployeeRecord.txt", "rw")) {
			employeedata.seek(0);
			recordsnum=employeedata.readInt();
			for(int i=0;i<recordsnum;i++) {
				IndexedIds.put(employeedata.readLong(),employeedata.readLong());
			}
			
			employeedata.seek(IndexedIds.get(id));
			String targetEmployee=employeedata.readUTF();
			System.out.println(targetEmployee);
			
			String [] arr=targetEmployee.trim().substring(1,targetEmployee.length()-1).split(",");
			
			for(String str:arr) {
				if(str.matches("salary:")) {
					double salary=Double.parseDouble(str.substring(str.indexOf(":")+1).trim());
					double salary1=salary+0.5*salary;
					str.replaceFirst("salary:"+salary,"salary:"+salary1);
				}
			}
			
			String finalstr=Arrays.stream(arr)
					   .collect(Collectors.joining(","));
			
			finalstr="{"+finalstr+"}";			
			employeedata.writeUTF(finalstr);
			System.out.println("Updated record of the employee");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		

	}

}
