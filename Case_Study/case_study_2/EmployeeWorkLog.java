package Apachedemo;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.groupingBy;




import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class EmployeeWorkLog {
    private String employeeId;
    private String name;
    private String department;
    private String projectId;
    private String timeperiod="";
    public EmployeeWorkLog(String employeeId, String name, String department, String projectId, LocalDate date,
			String taskCategory, double hoursWorked, String remarks) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.department = department;
		this.projectId = projectId;
		this.date = date;
		this.taskCategory = taskCategory;
		this.hoursWorked = hoursWorked;
		this.remarks = remarks;
		
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getTaskCategory() {
		return taskCategory;
	}

	public void setTaskCategory(String taskCategory) {
		this.taskCategory = taskCategory;
	}

	public double getHoursWorked() {
		return hoursWorked;
	}

	public void setHoursWorked(double hoursWorked) {
		this.hoursWorked = hoursWorked;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public String getTimeperiod() {
		return timeperiod;
	}

	public void setTimeperiod(String timeperiod) {
		this.timeperiod = timeperiod;
	}



	private LocalDate date;
    private String taskCategory;
    private double hoursWorked;
    private String remarks;
    
    
    
    
    

	@Override
	public String toString() {
		return "EmployeeWorkLog [employeeId=" + employeeId + ", name=" + name + ", department=" + department
				+ ", projectId=" + projectId + ", date=" + date + ", taskCategory=" + taskCategory + ", hoursWorked="
				+ hoursWorked + ", remarks=" + remarks + timeperiod+"]";
	}

	public static void main(String[] args) {
		
		String csvpath="C://Users//Santhosh//Downloads//employee_work_log.csv.xlsx"; 
 
		List<List<String>> records=new ArrayList<>();
	
		
		try(XSSFWorkbook wb =new XSSFWorkbook(new FileInputStream(csvpath))){
			XSSFSheet sh=wb.getSheetAt(0);
			String data=sh.getRow(500).getCell(4).toString();
			System.out.println(data);
			int rowcount=sh.getPhysicalNumberOfRows();
			for(int i=0;i<rowcount;i++) {
				XSSFRow row=sh.getRow(i);
				List<String> eachrowlist=new ArrayList<>();
				int cellcount=row.getPhysicalNumberOfCells(); 
				for(int j=0;j<cellcount;j++) {
					eachrowlist.add(sh.getRow(i).getCell(j).toString());
					
				}
				records.add(eachrowlist);
			}
			
		}catch(IOException e) {
			System.out.println("can not read file"+e);
		}
		records.remove(0);
		System.out.println(records);
		Map<LocalDate,Double> newmapformerging2sheets=new HashMap<>(); 
		List<EmployeeWorkLog>Log=new ArrayList<>();
		Set<String> set=new HashSet<>();
		for(List<String> record:records) {
	       set.add(record.get(0));
			Log.add(new EmployeeWorkLog(record.get(0),record.get(1),record.get(2),record.get(3),LocalDate.parse(record.get(4),DateTimeFormatter.ofPattern("dd-MMM-yyyy")),record.get(5),Double.valueOf(record.get(6)),record.get(7)));
			newmapformerging2sheets.put(LocalDate.parse(record.get(4),DateTimeFormatter.ofPattern("dd-MMM-yyyy")), Double.valueOf(record.get(6)));
			
		}
		for(EmployeeWorkLog employee:Log) {
			System.out.println(employee);
		}
		 
		//challenge 1  (Top 5 employees with highest hours in last 60 days.)
		Map<Integer,Double> map=new HashMap<>();
		XSSFWorkbook workbookchallenges=new XSSFWorkbook();
		XSSFSheet sheetchallenge1=workbookchallenges.createSheet("new_Employee_log_Highesht_hours_past_60days");
		
		
		int count=set.size();
		for(int i=1;i<=count;i++) {
			String empid="E000"+i; 
			Double highesthours=Log.stream().filter(s->(s.getEmployeeId().equals(empid) && s.getDate().isAfter(LocalDate.of(2025, 5, 11)))).mapToDouble(s->s.getHoursWorked()).reduce(0.0,(a,b)->a+b);
			map.put(i, highesthours);
		}
		
		Map<Integer,Double> sorted =map.entrySet().stream()
			       						.sorted(Map.Entry.<Integer,Double>comparingByValue().reversed())
			       						.limit(5).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,(e1,e2)-> e1, LinkedHashMap::new));
		int rowforchallenge1=1;
		for (Map.Entry<Integer, Double> entry : sorted.entrySet()) {
			Integer key=entry.getKey();
			Double value=entry.getValue();
			System.out.println(" "+key+"  "+value);
			
			List<EmployeeWorkLog> loglist=Log.stream().filter(s->s.getEmployeeId().equals("E000"+key)).collect(Collectors.toList());
			System.out.println(loglist);
			sheetchallenge1.createRow(rowforchallenge1);
			sheetchallenge1.getRow(rowforchallenge1).createCell(0).setCellValue(loglist.getFirst().getEmployeeId());
			sheetchallenge1.getRow(rowforchallenge1).createCell(1).setCellValue(loglist.getFirst().getName());
			sheetchallenge1.getRow(rowforchallenge1).createCell(2).setCellValue(value);
			rowforchallenge1++;
		}
		
		
		//challenge 2 (Sort by task category and hours descending; top 3 most time-consuming tasks per department.)
		Map<String,List<EmployeeWorkLog>> department_map=Log.stream().collect(groupingBy(EmployeeWorkLog::getDepartment));
		
		
		XSSFSheet sheetchallenge2=workbookchallenges.createSheet("new_Employee_log_3_timetaking_task_per_department");
		int rowfordepartment=0;
		 for (Map.Entry<String, List<EmployeeWorkLog>> entry : department_map.entrySet()) {
			 
			 System.out.println(" "+entry.getKey());
			 entry.getValue().sort((o1,o2)->{
				 if(o1.getTaskCategory().compareTo(o2.getTaskCategory())>0) {
					 return 1;
				 }
				 else if(o1.getTaskCategory().compareTo(o2.getTaskCategory())<0) {
					 return -1;
				 }
				 else {
				 return Double.compare(o1.getHoursWorked(), o2.getHoursWorked());
				 }
				 
			 });
			 System.out.println(entry.getValue());
			 entry.getValue().sort((o1,o2)->Double.compare(o1.getHoursWorked(), o2.getHoursWorked()));
			 List<EmployeeWorkLog> listperdepartment=entry.getValue().stream().limit(3).collect(Collectors.toList());
			 for(EmployeeWorkLog employee:listperdepartment) {
				 sheetchallenge2.createRow(rowfordepartment);
			 sheetchallenge2.getRow(rowfordepartment).createCell(0).setCellValue(entry.getKey());
			 sheetchallenge2.getRow(rowfordepartment).createCell(1).setCellValue(employee.getEmployeeId());
			 sheetchallenge2.getRow(rowfordepartment).createCell(2).setCellValue(employee.getName());
			 sheetchallenge2.getRow(rowfordepartment).createCell(3).setCellValue(employee.getTaskCategory());
			 rowfordepartment++;

			 }
			 
			 
	    
	        }
		
		 
		 
		//challenge 3 (Daily average hours trend in last 30 working days per employee.)
		 Map<Integer,Double> mapforaveragetrend=new HashMap<>();

			XSSFSheet sheetchallenge3=workbookchallenges.createSheet("new_Employee_log_daily_avg_trend");
			
				 
				 for(int i=1;i<=count;i++) {
						String empid="E000"+i; 
						Double highesthours=Log.stream().filter(s->(s.getEmployeeId().equals(empid) && s.getDate().isAfter(LocalDate.of(2025, 6, 11)))).distinct().mapToDouble(s->s.getHoursWorked()).reduce(0.0,(a,b)->a+b);
						int nofworkingdays=(int)Log.stream().filter(s->(s.getEmployeeId().equals(empid) && s.getDate().isAfter(LocalDate.of(2025, 6, 11)))).mapToDouble(s->s.getHoursWorked()).count();
						mapforaveragetrend.put(i, highesthours/nofworkingdays);
						System.out.println(highesthours/nofworkingdays+" "+mapforaveragetrend.get(i));
						
						
					}
				 
				 Map<Integer,Double> sortedavgtrend =mapforaveragetrend.entrySet().stream()
						 .sorted(Map.Entry.<Integer,Double>comparingByValue().reversed()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,(e1,e2)-> e1, LinkedHashMap::new));
					
					
					int rowforchallenge3=0;
					for (Map.Entry<Integer, Double> entry : sortedavgtrend.entrySet()) {
						Integer key=entry.getKey();
						Double value=entry.getValue();
						System.out.println(" "+key+"  "+value+"hi");
						List<EmployeeWorkLog> loglist=Log.stream().filter(s->s.getEmployeeId().equals("E000"+key)).collect(Collectors.toList());
						System.out.println(loglist);
						sheetchallenge3.createRow(rowforchallenge3);
						sheetchallenge3.getRow(rowforchallenge3).createCell(0).setCellValue(loglist.getFirst().getEmployeeId());
						sheetchallenge3.getRow(rowforchallenge3).createCell(1).setCellValue(loglist.getFirst().getName());
						sheetchallenge3.getRow(rowforchallenge3).createCell(2).setCellValue(value);
						rowforchallenge3++;

						
					}
					
		
		
			//challenge 4  (Combine 2 Excel sheets; compute hour differences.)
			
			
			
			
			
			
			int cellcount2=0;
			String csvpath2="C://Users//Santhosh//Downloads//employee_work_log_v2.csv.xlsx"; 
			List<List<String>> records2=new ArrayList<>();
			
			
			try(XSSFWorkbook wb2 =new XSSFWorkbook(new FileInputStream(csvpath2))){
				XSSFSheet sh2=wb2.getSheetAt(0);
				int rowcount2=sh2.getPhysicalNumberOfRows();
				for(int i=0;i<rowcount2;i++) {
					XSSFRow row2=sh2.getRow(i);
					List<String> eachrowlist2=new ArrayList<>();
					cellcount2=row2.getPhysicalNumberOfCells(); 
					for(int j=0;j<cellcount2;j++) {
						eachrowlist2.add(sh2.getRow(i).getCell(j).toString());
						
					}
					records2.add(eachrowlist2);
				}
				
			}catch(IOException e) {
				System.out.println("can not read file"+e);
			}
			
			
			records2.remove(0);
			System.out.println(records2);
			
			List<EmployeeWorkLog>Log2=new ArrayList<>();
			Set<String> set2=new HashSet<>();
			for(List<String> record:records2) {
				
		       set2.add(record.get(0));
				Log2.add(new EmployeeWorkLog(record.get(0),record.get(1),record.get(2),record.get(3),LocalDate.parse(record.get(4),DateTimeFormatter.ofPattern("dd-MMM-yyyy")),record.get(5),Double.valueOf(record.get(6)),record.get(7)));
				

			}
			for(EmployeeWorkLog employee:Log2) {
				System.out.println(employee);
			}
			
			XSSFWorkbook workbook=new XSSFWorkbook();
			XSSFSheet sheet=workbook.createSheet("new_Employee_log");
			int newrow=0;
			
			
			for(EmployeeWorkLog employee:Log) {
				sheet.createRow(newrow);
					
					sheet.getRow(newrow).createCell(0).setCellValue(employee.getEmployeeId());
					sheet.getRow(newrow).createCell(1).setCellValue(employee.getName());
					sheet.getRow(newrow).createCell(2).setCellValue(employee.getDepartment());
					sheet.getRow(newrow).createCell(3).setCellValue(employee.getProjectId());
					sheet.getRow(newrow).createCell(4).setCellValue(""+employee.getDate());
					sheet.getRow(newrow).createCell(5).setCellValue(employee.getTaskCategory());
					sheet.getRow(newrow).createCell(6).setCellValue(""+employee.getHoursWorked());
					sheet.getRow(newrow).createCell(7).setCellValue(employee.getRemarks());
					sheet.getRow(newrow).createCell(8).setCellValue(Math.abs(newmapformerging2sheets.get(employee.getDate())-employee.getHoursWorked()));
					newrow++;
					
					
				
			}
			for(EmployeeWorkLog employee:Log2) {
				sheet.createRow(newrow);
				
					sheet.getRow(newrow).createCell(0).setCellValue(employee.getEmployeeId());
					sheet.getRow(newrow).createCell(1).setCellValue(employee.getName());
					sheet.getRow(newrow).createCell(2).setCellValue(employee.getDepartment());
					sheet.getRow(newrow).createCell(3).setCellValue(employee.getProjectId());
					sheet.getRow(newrow).createCell(4).setCellValue(""+employee.getDate());
					sheet.getRow(newrow).createCell(5).setCellValue(employee.getTaskCategory());
					sheet.getRow(newrow).createCell(6).setCellValue(""+employee.getHoursWorked());
					sheet.getRow(newrow).createCell(7).setCellValue(employee.getRemarks());
					sheet.getRow(newrow).createCell(8).setCellValue(Math.abs(newmapformerging2sheets.get(employee.getDate())-employee.getHoursWorked()));

					newrow++;
					
					}
			
			try {
			File file=new File("C://Users//Santhosh//Desktop//1stweekchallenges//Public//new_log_employee_details.csv.xlsx");
			FileOutputStream fop=new FileOutputStream(file); 
			workbook.write(fop);
			}
			catch(IOException e) {
				System.out.println("can not write file "+e);
				
			}
			
			
			
			//challenge 5  (. Time period-based grouping (morning, afternoon, evening).)
			
			
			XSSFSheet sheetchallenge5=workbookchallenges.createSheet("new_Employee_log_time_period_based");

			
			int cellcount3=0;
			List<List<String>> records3=new ArrayList<>();
			String csvpath3="C://Users//Santhosh//Downloads//employee_work_logs_v2_with_time_period.csv.xlsx";
			try(XSSFWorkbook wb3 =new XSSFWorkbook(new FileInputStream(csvpath3))){
				XSSFSheet sh3=wb3.getSheetAt(0);
				int rowcount3=sh3.getPhysicalNumberOfRows();
				for(int i=0;i<rowcount3;i++) {
					XSSFRow row3=sh3.getRow(i);
					List<String> eachrowlist3=new ArrayList<>();
					cellcount3=row3.getPhysicalNumberOfCells(); 
					for(int j=0;j<cellcount3;j++) {
						eachrowlist3.add(sh3.getRow(i).getCell(j).toString());
						
					}
					records3.add(eachrowlist3);
				}
				
			}catch(IOException e) {
				System.out.println("can not read file"+e);
			}
			
			
			records3.remove(0);
			System.out.println(records3);
			
			List<EmployeeWorkLog>Log3=new ArrayList<>();
			for(List<String> record:records3) {
				
				Log3.add(new EmployeeWorkLog(record.get(0),record.get(1),record.get(2),record.get(3),LocalDate.parse(record.get(4),DateTimeFormatter.ofPattern("dd-MMM-yyyy")),record.get(5),Double.valueOf(record.get(6)),record.get(7)));
				Log3.getLast().setTimeperiod(record.get(9));

			}
			System.out.println("time based"+Log3);
			Map<String,List<EmployeeWorkLog>> timestamp_based_map=Log3.stream().collect(groupingBy(EmployeeWorkLog::getTimeperiod));
			List<String> listfortimebased=new ArrayList<>();
			listfortimebased.add("Morning");
			listfortimebased.add("Afternoon");
			listfortimebased.add("Evening");
			int rowfortimeperiod=0;
			for(String timeperiod:listfortimebased) {
				for(EmployeeWorkLog employee:timestamp_based_map.get(timeperiod)) {
					sheetchallenge5.createRow(rowfortimeperiod);
					sheetchallenge5.getRow(rowfortimeperiod).createCell(0).setCellValue(employee.getEmployeeId());
					sheetchallenge5.getRow(rowfortimeperiod).createCell(1).setCellValue(employee.getName());
					sheetchallenge5.getRow(rowfortimeperiod).createCell(2).setCellValue(employee.getDepartment());
					sheetchallenge5.getRow(rowfortimeperiod).createCell(3).setCellValue(employee.getProjectId());
					sheetchallenge5.getRow(rowfortimeperiod).createCell(4).setCellValue(""+employee.getDate());
					sheetchallenge5.getRow(rowfortimeperiod).createCell(5).setCellValue(employee.getTaskCategory());
					sheetchallenge5.getRow(rowfortimeperiod).createCell(6).setCellValue(""+employee.getHoursWorked());
					sheetchallenge5.getRow(rowfortimeperiod).createCell(7).setCellValue(employee.getRemarks());
					sheetchallenge5.getRow(rowfortimeperiod).createCell(8).setCellValue(employee.getTimeperiod());
					rowfortimeperiod++;
				}
			}
			
			
			try {
				File file=new File("C://Users//Santhosh//Desktop//1stweekchallenges//Public//new_log_employee_challenges.csv.xlsx");
				FileOutputStream fop=new FileOutputStream(file); 
				workbookchallenges.write(fop);
				}
				catch(IOException e) {
					System.out.println("can not write file "+e);
					
				}
			
			
			
			
			
			
			
			
			
						
						
			
			
	
		    
		    
		    
		    
		    
			

			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		 
		 
		 
		 
		 
		 
		 
		 
		
		
	}

	
	

	
	
}
