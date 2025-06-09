import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
class Pair implements Comparable<Pair>{
	public double hrs;
	public String emp;
	public Pair (double hrs,String emp) {
		this.hrs=hrs;
		this.emp=emp;
	}
	@Override
	public int compareTo(Pair o) {
		return Double.compare(o.hrs,this.hrs);
	}
}
public class ProblemNo9 {
     public static void getData(List<EmployeeData>val) throws IOException {
    	 Map<String,Double>mp=new HashMap<>();
    	 for(EmployeeData ele:val) {
    		 LocalDate dateLimit = LocalDate.now().minusDays(60);
    		 if (ele.getDate().isAfter(dateLimit) || ele.getDate().isEqual(dateLimit)) {
    		     mp.put(ele.getEmployeeId(),mp.getOrDefault(ele.getEmployeeId(),(double)0)+ele.getHoursWorked());
    		 }
    	 }
    	 List<Pair>ans=new ArrayList<>();
    	 for(Map.Entry<String,Double>entry:mp.entrySet()) {
    		 ans.add(new Pair(entry.getValue(),entry.getKey()));
    	 }
    	 Collections.sort(ans);
    	 
    	 try(BufferedWriter a=new BufferedWriter(new FileWriter("TestCase9.csv"))){
    		 a.append("EMPLOYEEID,TOTALWORKINGHRS\n");
    		 int cnt=0;
    		 for(Pair ele:ans) {
    			 if(cnt==5)break;
    			 cnt++;
    			 String vals=ele.emp+","+Double.toString(ele.hrs)+"\n";
    			 a.append(vals);
//    			 System.out.println("added");
    		 }
    	 }
    	 catch(Exception e) {
    		 e.printStackTrace();
    	 }
     }
}
