package Day10;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;


public class JsonChallenge {
	
	static class Student{
		private  int studentId;
		private  static final String[] countryCodes= {"AU","IN","US","EU"};
		private  String countryCode;
		private  int enrolledMonth;
		private  int enrolledYear;
		private  int age;
		private  String[] genders= {"M","F"};
		private  String gender;
		private  boolean expe;
		private  Random r=new Random();
		
		public Student() {
			this.studentId=r.nextInt(10000,15000);
			this.countryCode=countryCodes[r.nextInt(countryCodes.length)];
			this.enrolledMonth=r.nextInt(1,13);
			this.enrolledYear=LocalDate.now().getYear()-r.nextInt(0,12);
			this.age=r.nextInt(18,50);
			this.gender=genders[r.nextInt(genders.length)];
			this.expe=r.nextBoolean();
		}
		@Override
		public String toString() {
			JsonToStringBuilder builder = new JsonToStringBuilder(this);
			builder.append("studentId", studentId).append("countryCode", countryCode).append("enrolledMonth", enrolledMonth).append("enrolledYear", enrolledYear)
			.append("age", age).append("gender", gender).append("expe", expe);
					
			return builder.build();
		}

	}
public static void main(String[] args)throws Exception {
		List<Student> students=Stream.generate(Student::new)
				.limit(1000)
				.distinct()
				.toList();
		
		String data=students.toString();
		//Path studentsData=Paths.get("C:\\Users\\vejas\\OneDrive\\Desktop\\medplus\\mp-training\\training\\java\\core\\Day10\\src\\Day10\\studentsData.json");
		//Files.createFile(studentsData);
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\vejas\\OneDrive\\Desktop\\medplus\\mp-training\\training\\java\\core\\Day10\\src\\Day10\\studentsData.json"))) {
			writer.write(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

