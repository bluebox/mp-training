package Day10_09_07_Practice;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileClass {
	public static void main(String[] args) {
		String prac="prac.txt";
		File f=new File(prac);
		if(!f.exists()) {
			System.out.println("No file exists creating the file");
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("File name :" + f.getName());
        System.out.println("Path: " + f.getPath());
        System.out.println("Absolute path:" + f.getAbsolutePath());
        System.out.println("Parent:" + f.getParent());

        System.out.println("Is writable:" + f.canWrite());
        System.out.println("Is readable: " + f.canRead());
        System.out.println("Is a directory:" + f.isDirectory());
        System.out.println("File Size in bytes " + f.length());
        
        
        System.out.println(f.getTotalSpace()/(1024*1024*8));
        System.out.println(f.list());
        //BufferedReader br=new BufferedReader(new );
        System.out.println("buffered input stream reader:");
        try(BufferedInputStream br=new BufferedInputStream(new FileInputStream(prac))) {
        		int data;
        		System.out.println("Reading data from file");
        		while((data=br.read())!=-1 && data!=(int)'\n') {//prints only first line of the file
        			System.out.print((char) data);
        		}
        		}
        catch(IOException e) {
        		System.out.println(e);
        }
        System.out.println("Buffered reader:");
        try(BufferedReader bfr=new BufferedReader(new FileReader(prac))){
        	String line;
        		while((line=bfr.readLine())!=null){
        			System.out.println(line);
        		}
        }catch(IOException ioe) {
        	System.out.println(ioe);
        }
        //Scanner class
        try {
        		System.out.println("Scanner class:");
			Scanner filescanner=new Scanner(f);
			while(filescanner.hasNextLine()) {
				String line=filescanner.nextLine();
				System.out.println(line);
			}
			filescanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        
        
        try {
            FileWriter Writer = new FileWriter("myfile.txt");
            Writer.write("Files in Java are ");
            Writer.close();
            System.out.println("Successfully written.");
        }
        catch (IOException e) {
            System.out.println("An error has occurred.");
            e.printStackTrace();
        }
        String data = "This is some data to be written using BufferedOutputStream.";
        try (BufferedOutputStream bufferedOut = new BufferedOutputStream(new FileOutputStream("output.txt"))) {
            bufferedOut.write(data.getBytes()); 
            System.out.println("Data written to buffer. It will be flushed when buffer is full or stream is closed/flushed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String fileName = "output.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("This is the first line.");
            writer.newLine(); // Writes a platform-independent new line
            writer.write("This is the second line.");
            writer.flush(); // Forces buffered data to be written
            writer.write("This is the third line.");
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
	}
}
