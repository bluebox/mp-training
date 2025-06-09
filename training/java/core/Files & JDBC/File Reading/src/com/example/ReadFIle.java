package com.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class ReadFIle {
	public static void main(String[] args) throws IOException {
		Path p=Path.of("/home/developer/eclipse-workspace/Bhanu/mp-training/training/java/core/Files & JDBC/File Reading/Hi");
		//Creates a file if not there
		System.out.println(p.getFileName());
		File f=new File("/home/developer/eclipse-workspace/Bhanu/mp-training/training/java/core/Files & JDBC/File Reading/Hi Friends");
		if(f.createNewFile()) { //Creates a file if not there
			System.out.println("File is created");
		}
		else {
			System.out.println("File already exists");
		}
		System.out.println(f.isFile());
		System.out.println(f.canRead()+" "+f.canWrite());
		FileInputStream fis=new FileInputStream(f);
		FileOutputStream fout=new FileOutputStream(f);
		fout.write("Hello World\nWelcome to my kingdom.".getBytes());
		int i;
		ArrayList<String> l=new ArrayList<String>();
		String x="";
		while((i=fis.read())!=-1) {
			if((char)i==' ' || (char)i=='\n' ||(char)i=='\t') {
				l.add(x.toLowerCase().trim());
				x="";
			}
			else if((i>=65&&i<=90)||(i>=97&&i<=122)) {
				x+=(char) i;
			}
		}
		l.add(x.toLowerCase().trim());
		System.out.println(l);
		Map<Object, Long> y = l.stream().filter(i1->i1.length()>5).collect(Collectors.groupingBy(l1->l1, Collectors.counting()));
		System.out.println(y);
	}
}
