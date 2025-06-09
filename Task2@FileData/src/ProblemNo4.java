import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Month;
import java.util.*;
//class Pair{
//	String name;
//	String id;
//	public Pair(String name,String id) {
//		this.name=name;
//		this.id=id;
//	}
//}
public class ProblemNo4 {
    public static void getData(List<EmployeeData> val) throws IOException {
    	 Map<String,Map<Integer,Double>>mp=new HashMap<>();
    	 for(EmployeeData ele:val) {
    		 String name=ele.getName();
    		 mp.putIfAbsent(name,new HashMap<>());
    		 Month month=ele.getDate().getMonth();
    		 int monthNo=month.getValue();
    		 mp.get(name).put(monthNo,mp.get(name).getOrDefault(monthNo,(double)0)+ele.getHoursWorked());
    	 }
    	 try(BufferedWriter a=new BufferedWriter(new FileWriter("TestCase4.csv"))){
    		 String vals="NAME,JAN,FEB,MAR,APR,MAY,JUNE,JULY,AUG,SEP,OCT,NOV,DEC\n";
    		 a.append(vals);
    		 for(Map.Entry<String,Map<Integer,Double>>entry:mp.entrySet()) {
        		 String string=entry.getKey()+",";
        		 for(int i=1;i<=12;i++) {
        		     double cnt=mp.get(entry.getKey()).getOrDefault(i,(double)0);
        		     if(cnt>0)string+=Double.toString(cnt/4)+",";
        		     else string+=",,";
        		 }
        		 string+="\n";
        		 a.append(string);
//        		 System.out.println("added");
        	 }
    	 }
    	 catch(Exception e) {
    		 e.printStackTrace();
    	 }
    }
}
