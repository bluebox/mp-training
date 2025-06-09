import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.*;

public class CSVReader {
     public static List<EmployeeData> getData(){
    	 List<EmployeeData> val=new ArrayList<>();
         try(BufferedReader f=new BufferedReader(new FileReader("Data.csv"))){
        	 String ch;
        	 int cnt=0;
        	 while((ch=f.readLine())!=null) {
        		if(cnt==0) {
        			cnt++;
        			continue;
        		}
        		String []ans=ch.split(",");
        		String employeeId=ans[0];
        		String name=ans[1];
        		String department=ans[2];
        		String projectId=ans[3];
        		LocalDate date=LocalDate.parse(ans[4]);
        		String taskCategory=ans[5];
        		double hoursWorked=Double.parseDouble(ans[6]);
        		String remarks=ans[7];
        		EmployeeData vals=new EmployeeData(employeeId,name,department,projectId,date,taskCategory,hoursWorked,remarks);
        		val.add(vals);
        	 }
         }
         catch(Exception e){
        	 e.printStackTrace();
         }
		return val;
     }
}
