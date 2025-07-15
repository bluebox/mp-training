package CaseStudy;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.IsoFields;
import java.io.File;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class CaseStude_2 {

	public static void main(String[] args) {
		List<Employee> employeeslogs=new ArrayList<>();
		int count=0;
		
		try {
			FileInputStream file = new FileInputStream(new File("C:\\Users\\DELL\\Downloads\\Employee_Timesheet_May_to_July_2025_FormattedDate.xlsx"));
			try {
				XSSFWorkbook workbook = new XSSFWorkbook(file);
			    
				 XSSFSheet sheet = workbook.getSheetAt(0);
				 System.out.println(sheet);
				 Iterator<Row> rowIterator = sheet.iterator();
				 
				 while (rowIterator.hasNext()) {

		                Row row = rowIterator.next();

		                // For each row, iterate through all the
		                // columns
		                Iterator<Cell> cellIterator
		                    = row.cellIterator();
		                ArrayList<String> record=new ArrayList<>();
		                int ind=0;
		                while (cellIterator.hasNext()) {
                          
		                    Cell cell = cellIterator.next();
		                    if(cell.getCellType()==CellType.NUMERIC && ind!=4 ) {
		                    	//System.out.println(1);
		                    	record.add(Double.toString(cell.getNumericCellValue()));
		                    }else {
                            record.add(cell.getStringCellValue());   
		                    }
		                    ind++;
		                }
		                count=count+1;
		              //  System.out.println(record) ;
		      if(count!=1) { employeeslogs.add(new Employee(record.get(0),record.get(1),record.get(2),record.get(3),LocalDate.parse(record.get(4),DateTimeFormatter.ofPattern("dd-MMM-yyyy")),
		            		   record.get(5),Double.parseDouble(record.get(6)),record.get(7)));
				 }
				 }
		            file.close();
		            
		            XSSFWorkbook Outputworkbook = new XSSFWorkbook();

		            // Creating a blank Excel sheet
		            XSSFSheet sheet1
		                = Outputworkbook.createSheet("outputSheet1");  
		           
				
	//1. Engineering employees working on >2 projects: group by employee ID and count unique projects.
		          Set<String> set=new HashSet<>() ;
		         var result=employeeslogs.stream()
		        		   .sorted(Comparator.comparing(Employee::getEmployeeId)) 
		        		   .filter(s->{if(set.contains(s.getProjectId()+s.getEmployeeId())) {
		        			   return false;
		        		   }else{set.add(s.getProjectId()+s.getEmployeeId());
		        		   return true;
		        		   }})
		        		   .collect(Collectors.groupingBy(Employee::getEmployeeId
		        				   ,Collectors.mapping(Employee::getProjectId, Collectors.toList())));
		         
		         try(BufferedWriter writer = new BufferedWriter(new FileWriter("output1.txt")); // 'true' for append mode
			              ) {
	                    writer.write("EmployeeId,ProjectList");
	                    writer.newLine();
		                  result.entrySet().stream()
				         .filter(s->s.getValue().size()>=2)
				         .forEach(s->{
				        	 try {
				        	writer.write(s.getKey()+","+s.getValue());
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
				        	  try {
								writer.newLine();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} // Writes a platform-specific line separato
                                System.out.println(s.getKey()+","+s.getValue()) ; 
				                 System.out.println("Successfully wrote to the file using BufferedWriter.");
				             }
				        	 
				         );}catch(IOException e) {
				        	 e.printStackTrace();
				         }
		         int rownum_1=0;     
	               // writing to csv file 
		         for(String key:result.keySet()) {
		             sheet1.createRow(rownum_1);
		        	 int cellnum = 0;
		        	 sheet1.getRow(rownum_1).createCell(cellnum++).setCellValue(key);
		        	 for(String values:result.get(key)) {
		        		 sheet1.getRow(rownum_1).createCell(cellnum++).setCellValue(values);
		        	 }
		        	 rownum_1++;
		        	 
		         }
		         
		        
		         
		         XSSFSheet sheet2
	                = Outputworkbook.createSheet("outputSheet2");  
		        		   
		          //2. Weekly effort per project from sorted date logs.
		        		 LocalDate today = LocalDate.now();
		        		 LocalDate threeMonthsAgo = today.minusMonths(3);
		        		 // Calculate total hours worked per project per week within the last three months
		        		 Map<String, Map<Object, Double>> workPerProjectWeekly = employeeslogs.stream()
		        		     .filter(emp -> !emp.getDate().isBefore(threeMonthsAgo)) // Filter by 3-month range
		        		     .collect(Collectors.groupingBy(
		        		         Employee::getProjectId, 
		        		         Collectors.groupingBy(
		        		             emp -> emp.getDate().get(IsoFields.WEEK_OF_WEEK_BASED_YEAR), // Group by week of year
		        		             Collectors.summingDouble(Employee::getHoursWorked) // Sum hours worked per week
		        		         )
		        		     ));

		        		  try(BufferedWriter writer = new BufferedWriter(new FileWriter("output2.txt")); // 'true' for append mode
					              ) {
			                    writer.write("ProjectId,Week,hoursperWeek");
			                    writer.newLine();
		        		 //writing and Printing the result
		        		 workPerProjectWeekly.forEach((projectId, weeklyHours) -> {
		        		     System.out.println("Project ID: " + projectId);
		        		     weeklyHours.forEach((week, totalHours) -> {
		        		         System.out.println("ProjectID: "+projectId+",Week: " + week + ",Total Hours Worked: " + totalHours);
		        		         try {
									writer.write(""+projectId+"," + week + "," + totalHours);
								    writer.newLine();
		        		         } catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
		        		     } );
		        		 });
		        		  }catch(IOException e) {
					        	 e.printStackTrace();
					         }

		        		  int rownum_2=0;     
			               // writing to csv file 
				         for(String key:workPerProjectWeekly.keySet()) {
				             sheet2.createRow(rownum_2);
				        	 int cellnum = 0;
				        	 sheet2.getRow(rownum_2).createCell(cellnum++).setCellValue(key);
				        	 for(Entry<Object, Double> values:workPerProjectWeekly.get(key).entrySet()) {
				        		 if(cellnum==1) {
				        			 sheet2.getRow(rownum_2).createCell(cellnum++).setCellValue(values.getKey().toString());
				        		 }
				        		 sheet2.getRow(rownum_2).createCell(cellnum++).setCellValue(values.getValue());
				        	 }
				        	 rownum_2++;
				        	 
				         }
				         
				       
				         XSSFSheet sheet3
			                = Outputworkbook.createSheet("outputSheet3");  
				        		     
		        		  
		          
		            //3. Find employees with 3+ zero-hour consecutive days.
		           Map<String,List<LocalDate>> map_3=employeeslogs.stream()
		                .sorted(Comparator.comparing(Employee::getDate))
		               .filter((v)->(v.getHoursWorked()==0))
		               .collect(Collectors.groupingBy(Employee::getEmployeeId,Collectors.mapping(Employee::getDate, Collectors.toList())));
		           List<String> list=new ArrayList<>();
		           try(BufferedWriter writer = new BufferedWriter(new FileWriter("output3.txt")); 
				              ) {
		                    writer.write("EmployeeId");
		                    writer.newLine();
		           map_3.entrySet().forEach(s->{
		            	 List<LocalDate> dates= s.getValue();
		            	 for(int i=0;i<dates.size()-3;i++) {
		            		 Period period_3=Period.between(dates.get(i+2), dates.get(i+3));
		            		 Period period = Period.between(dates.get(i+1), dates.get(i+2)); 
		            		 Period period_2=Period.between(dates.get(i), dates.get(i+1));
		            		 if(period_3.getDays()==1 && period.getDays()==1 && period_2.getDays()==1) {
		            			 System.out.println(s.getKey());
		            			 try {
		            				 list.add(s.getKey());
		     		            	writer.write(s.getKey());
		     		            	writer.newLine();
		     		            		System.out.println(s);
		     		            	 } catch (IOException e) {
		     								// TODO Auto-generated catch block
		     								e.printStackTrace();
		     		            	 }
		            			 System.out.println("Required employee ID");
		            			 break;
		            		 }
		            	 }
		              }); }catch(IOException e) {
				        	 e.printStackTrace();
				         }
		           
		           int rownum_3=0; 
		           int cellnum = 0;
	               // writing to csv file 
		         for(String key:list) {
		             sheet3.createRow(rownum_3);
		        	 sheet3.getRow(rownum_3).createCell(cellnum++).setCellValue(key);
		        	 rownum_3++;	 
		         }
		         
		         XSSFSheet sheet4
	                = Outputworkbook.createSheet("outputSheet4");  
		        		     
     		  
		              
		            //4. Detect and remove duplicate logs.
		              try(BufferedWriter writer = new BufferedWriter(new FileWriter("output4.txt")); // 'true' for append mode
				              ) {
		                    writer.write("EmployeeId,EmployeeName,Department,ProjectId");
		                    writer.newLine();
		              employeeslogs.stream()
		              .distinct()
		              .forEach(
		            	s->{s.toString();	
		            	 try {
		            	writer.write(s.getEmployeeId()+","+s.getName()+","+s.getDepartment()+","+s.getProjectId());
		            	    writer.newLine();
		            		System.out.println(s);
		            	 } catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
		            	 }	});}catch(IOException e) {
				        	 e.printStackTrace();
				         }
		              
		             List<Employee>  list_4= employeeslogs.stream()
				              .distinct()
				              .collect(Collectors.toList());
		             
		             
		             int rownum_4=0; 
			           int cellnum_4 = 0;
		               // writing to csv file 
			         for(Employee key:list_4) {
			             sheet4.createRow(rownum_4);
			        	 sheet4.getRow(rownum_4).createCell(cellnum_4).setCellValue(key.toString());
			        	 rownum_4++;	 
			         }
		              

		              XSSFSheet sheet5
		                = Outputworkbook.createSheet("outputSheet5");  
			        		     
		            //5 .Days with <2 hours; group by employee and date.
				    Map<String,List<Employee>> map_5=employeeslogs.stream()
				    		     .sorted(Comparator.comparing(Employee::getDate))
				                 .sorted(Comparator.comparing(Employee::getEmployeeId))
				                 .distinct()
				                 .filter(s->s.getHoursWorked()<(double)2)
				                 .collect(Collectors.groupingBy(
				                     Employee::getEmployeeId
				                 ));
				    
				    try(BufferedWriter writer = new BufferedWriter(new FileWriter("output5.txt")); // 'true' for append mode
				              ) {
		                    writer.write("EmployeeId,NumberofDays");
		                    writer.newLine();
		            map_5.entrySet().forEach(s->{
		            	System.out.println(s.getKey()+" "+s.getValue().size()+"days");
		            	try {
			            	writer.write(s.getKey()+","+s.getValue().size()+"days");
			            	    writer.newLine();
			            		//System.out.println(s.getKey()+","+s.getValue().size()+"days");
			            	 } catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
			            	 }
		            });
				    }catch(IOException e) {
		        	 e.printStackTrace();
		         }
				    
				    int rownum_5=0; 
			           
		               // writing to csv file 
			         for(Entry<String,List<Employee>> key:map_5.entrySet()) {
			             sheet5.createRow(rownum_5);
			             int cellnum_5 = 0;
			             sheet5.getRow(rownum_5).createCell(cellnum_5++).setCellValue(key.getKey());
			             sheet5.getRow(rownum_5).createCell(cellnum_5++).setCellValue(key.getValue().size());
				        	rownum_5++;	 
			         }

		            
				    try {

			             // Writing the workbook
			             FileOutputStream out = new FileOutputStream(
			                 new File("Output.xlsx"));
			             Outputworkbook.write(out);

			             // Closing file output connections
			             out.close();
			             Outputworkbook.close();

			             // Console message for successful execution of
			             // program
			             System.out.println(
			                 "OutputWorkbook.xlsx written successfully on disk.");
			         }

			         // Catch block to handle exceptions
			         catch (Exception e) {

			             // Display exceptions along with line number
			             // using printStackTrace() method
			             e.printStackTrace();
			         }   
		            
			
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
