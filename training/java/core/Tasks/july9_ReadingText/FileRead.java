package corejava.july9_ReadingText;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileRead {

	public static void main(String[] args) {
		File myFile=new File("Sample.txt");
		try {
			if(myFile.createNewFile()) {
				System.out.println("File created");
			}
			else {
				System.out.println("File already exists!");
			}
			System.out.println(myFile.getAbsolutePath());
			Scanner fileData=new Scanner(myFile);
			Map<String,Integer> frequencies=new HashMap<>();
			
			while(fileData.hasNextLine()) {
				String line=fileData.nextLine();
				line=line.replaceAll("[^a-zA-Z0-9\\s]", "");
				String[] tokens=line.split("\\s+");
				for(String token:tokens) {
					if((!token.isEmpty())&& token.length()>5) {
						System.out.println(token);	
					}
					token=token.toLowerCase();
					frequencies.put(token, frequencies.getOrDefault(token, 0) + 1);
				}
			}
			for(String key:frequencies.keySet()) {
				System.out.println(key+" : "+frequencies.get(key));
			}
			fileData.close();
			
		}
		catch(IOException e) {
			e.printStackTrace();
			System.out.println("Something Went Wrong!");
		}
	}

}
