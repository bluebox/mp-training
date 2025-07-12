package randomAcessFileChallenge;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class RandomAcessFile {
	
	private static Map<Integer,Long> indexedIds = new HashMap<>();
	
	static {
		int totalRecords = 0;
		try (RandomAccessFile ra = new RandomAccessFile(".//src//randomAcessFileChallenge//employees_15.dat", "r")) {
			totalRecords = ra.readInt();
			System.out.print("employess Count : " + totalRecords);
			for(int i=0;i<totalRecords;i++) {
				indexedIds.put(ra.readInt(),ra.readLong());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		try (RandomAccessFile ra = new RandomAccessFile(".//src//randomAcessFileChallenge//employees_15.dat", "rw")) {
			Scanner scanner = new Scanner(System.in);
			List<Integer> ids = new ArrayList<>(indexedIds.keySet());
			Collections.sort(ids);
			while(true) {
				System.out.println(ids);
				System.out.println("Enter an Employee Id or 0 to exit");
				if(!scanner.hasNext()) {
					break;
				}
				int employeeId = Integer.parseInt(scanner.nextLine());
				if(employeeId <= 0) {
					break;
				}
				if(!ids.contains(employeeId)) {
					continue;
				}
				Employee e = readEmployee(ra,employeeId);
				System.out.println("Enter new salary, nothing if no change: ");
				
				try {
					double salary = Double.parseDouble(scanner.nextLine());
					ra.seek(indexedIds.get(employeeId)+4);
					ra.writeDouble(salary);
					readEmployee(ra,employeeId);
				}catch(NumberFormatException exception) {
					System.out.println("wrong id"+exception);
				}
			}
			scanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private static Employee readEmployee(RandomAccessFile ra, int employeeId) throws IOException {
		
		ra.seek(indexedIds.get(employeeId));
		
		int id = ra.readInt();
		double salary = ra.readDouble();
		String first = ra.readUTF();
		String last = ra.readUTF();
		
		Employee e = new Employee(id,first,last,salary);
		System.out.println(e);
		
		return e;
	}

}
