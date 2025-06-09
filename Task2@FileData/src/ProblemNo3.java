import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Month;
import java.util.*;

public class ProblemNo3 {
     public static void getData(List<EmployeeData>ans) throws IOException {
    	 Map<String,Double>mp=new HashMap<>();
    	 for(EmployeeData ele:ans) {
    		 if(ele.getDate().getMonth()==Month.MARCH || ele.getDate().getMonth()==Month.APRIL || ele.getDate().getMonth()==Month.MAY) {
    			 mp.put(ele.getProjectId(),mp.getOrDefault(ele.getProjectId(),(double)0)+ele.getHoursWorked());
    		 }
    	 }
    	
    	 File f=new File("TestCase3.csv");
    	 if(!f.exists())f.createNewFile();
    	 else f.delete();
    	 try(BufferedWriter a=new BufferedWriter(new FileWriter("TestCase3.csv",true))){
    		 String vals="ProjectId,"+"HoursWorked\n";
    		 a.append(vals);
    		 for(Map.Entry<String,Double> entry:mp.entrySet()) {
        		 if(entry.getValue()>=10) {
        			 // took working hours >=10 because there is no project with working hours >=100;
        			 String val=entry.getKey()+","+Double.toString(entry.getValue())+"\n";
        			 a.append(val);
//        			 System.out.println("data is entered");
        		 }
        	 }
    	 }
    	 catch(Exception e) {
    		 e.printStackTrace();
    	 }
     }
}
