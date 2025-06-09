import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class ProblemNo7 {
    public static void getData(List<EmployeeData>val) throws IOException {
    	Map<String,Map<LocalDate,Double>>mp=new HashMap<>();
    	for(EmployeeData ele:val) {
    		String employeeId=ele.getEmployeeId();
    		LocalDate date=ele.getDate();
    		mp.putIfAbsent(employeeId, new HashMap<>());
    		mp.get(employeeId).put(date,mp.get(employeeId).getOrDefault(date,(double) 0)+ele.getHoursWorked());
    	}

    	try(BufferedWriter a=new BufferedWriter(new FileWriter("TestCase7.csv"))){
    		a.append("EMPLOYEEID,LOCALDATE,WORKINGHOURS\n");
    		for(Map.Entry<String,Map<LocalDate,Double>>entry:mp.entrySet()) {
    			for(Map.Entry<LocalDate,Double>i:entry.getValue().entrySet()) {
    				if(i.getValue()>=10) {
    					String str=entry.getKey()+",";
    					str+=i.getKey().toString()+","+Double.toString(i.getValue())+"\n";
    					a.append(str);
//    					System.out.println("added");
    				}
    			}
    		}
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    }
}
