package Day10;

import java.io.*;

public class RandomAccessFileChallenge {
	
	 private static final int CHAR_SIZE = 1; 
	    private static final int NAME_LENGTH = 20;
	    private static final int RECORD_SIZE = 4 + 8 + NAME_LENGTH + NAME_LENGTH; 

	    public static void writeEmployee(RandomAccessFile file, int id, double salary, String firstName, String lastName) throws IOException {
	        file.writeInt(id);
	        file.writeDouble(salary);
	        writeFixedString(file, firstName, NAME_LENGTH);
	        writeFixedString(file, lastName, NAME_LENGTH);
	    }

	    public static void readEmployee(RandomAccessFile file, int index) throws IOException {
	        file.seek(index * RECORD_SIZE);
	        int id = file.readInt();
	        double salary = file.readDouble();
	        String firstName = readFixedString(file, NAME_LENGTH);
	        String lastName = readFixedString(file, NAME_LENGTH);

	        System.out.printf("ID: %d | Salary: %.2f | First Name: %s | Last Name: %s%n", id, salary, firstName, lastName);
	    }

	    private static void writeFixedString(RandomAccessFile file, String str, int length) throws IOException {
	        byte[] strBytes = str.getBytes();
	        byte[] buffer = new byte[length];
	        int len = Math.min(strBytes.length, length);
	        System.arraycopy(strBytes, 0, buffer, 0, len);
	        file.write(buffer);
	    }

	    private static String readFixedString(RandomAccessFile file, int length) throws IOException {
	        byte[] buffer = new byte[length];
	        file.readFully(buffer);
	        return new String(buffer).trim();
	    }

	    public static void main(String[] args) {
	        try (RandomAccessFile raf = new RandomAccessFile("employees.dat", "rw")) {
	            writeEmployee(raf, 101, 75000.0, "Alice", "Smith");
	            writeEmployee(raf, 102, 82000.0, "Bob", "Johnson");
	            writeEmployee(raf, 103, 91000.0, "Charlie", "Williams");

	            System.out.println("Reading record at index 2:");
	            readEmployee(raf, 2);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
}





